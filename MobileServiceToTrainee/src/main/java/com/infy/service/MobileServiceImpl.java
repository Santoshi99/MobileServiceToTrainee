package com.infy.service;

import java.util.List;
import java.util.ArrayList;
import com.infy.validator.Validator;
import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;
import java.time.LocalDateTime;


public class MobileServiceImpl implements MobileService{
	
	private MobileServiceDAO dao =  new MobileServiceDAOImpl();
        private Validator validator=new Validator();

	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws MobileServiceException {
		
		
		validator.validate(service);
		Float estimatedCost = calculateEstimateCost(service.getIssues());
		if(estimatedCost<=0) {
			throw new MobileServiceException("Sorry, we do not provide that service.");
		}
		else
		{
			service.setServiceFee(estimatedCost);
			service.setStatus(Status.ACCEPTED);
			service.setTimeOfRequest(LocalDateTime.now());
		}
		ServiceRequest result = dao.registerRequest(service);
		
		return result;
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		Float cost =0f;
		for(String issue: issues){
		    if(issue.equalsIgnoreCase("Battery"))
		            cost+=10;
		    else if(issue.equalsIgnoreCase("CAMERA")) 
		            cost+=5;
		    else if(issue.equalsIgnoreCase("SCREEN"))
		            cost+=15;
		}
		return cost;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		
		List<ServiceReport> report = new ArrayList<>(); 
		List<ServiceRequest> requests = dao.getServices();
		
		for(ServiceRequest req : requests) {
			
			if(req.getStatus()==status) {
				report.add(new ServiceReport(req.getServiceId(),req.getBrand(),req.getIssues(),req.getServiceFee()));
			}
		}
		if(report.isEmpty())
			throw new MobileServiceException("Sorry, we did not find any records for your query.");
		return report;
	}

}
