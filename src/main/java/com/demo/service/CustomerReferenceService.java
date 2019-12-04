package com.demo.service;

import com.demo.exceptions.ApplicationException;
import com.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerReferenceService {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8080/customers/";

    public Customer getCustomerData(Long id) throws ApplicationException{
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String url = baseUrl+id.toString();
        try{
            return restTemplate.exchange(url, HttpMethod.GET, entity, Customer.class).getBody();
        }catch (Exception e){
            throw new ApplicationException("Error occured while getting data");
        }

    }

    public List<Customer> getAllCustomers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(baseUrl, HttpMethod.GET, entity, List.class).getBody();
    }

    public Customer createCustomer(Customer customer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity <Customer> entity = new HttpEntity<Customer>(customer,headers);
        return restTemplate.postForObject(baseUrl,entity,Customer.class);
    }

    public void deleteCustomer(Long id){
        restTemplate.delete(baseUrl+id.toString());
    }
}
