package com.pay.pal.java;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentServices {
	
	private static final String client_id = "AfDu2HdA36AWIdKM2Dn6L4VYzhpWYYqBgqfxtgA4l072rDj8vpL44FHxIFjV98Rf6FHfnGnRpkbRGoTz";
	private static final String client_secret = "EPg3DlNSjegS5v4Xhq9sxGMDUDUBoxilLxAUqwF4dnWk13khy7b-fdJOrKra0RlZ8Rwa0R8cBrtW4a29";
	private static final String MODE = "sandbox";
	
	public String authorizaPayment(OrderDetail orderDatail) throws PayPalRESTException {
		
		Payer payer = getPayerInformation();		
		RedirectUrls redirectUrls = getRedirectUrls();
		
		List<Transaction> listTransaction = getTransactionInformation(orderDatail);
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction)
					  .setRedirectUrls(redirectUrls)
					  .setPayer(payer)
					  .setIntent("authorize");
		
		APIContext apiContext = new APIContext(client_id, client_secret, MODE);
		Payment approvalPayment = requestPayment.create(apiContext);
		
		System.out.println(approvalPayment);
		
		return getApprovalLInk(approvalPayment);
		
	}
	
	private String getApprovalLInk(Payment approvedPayment) {
         List<Links> links = approvedPayment.getLinks();
         
         String approvalLink = null;
         for(Links link: links) {
        	 if(link.getRel().equalsIgnoreCase("approval_url")) {
        		 approvalLink = link.getHref();
        	 }
         }
		
		return approvalLink;
	}

	private List<Transaction> getTransactionInformation(OrderDetail orderDetail){
		Details details = new Details();
		        details.setShipping(orderDetail.getShipping());
		        details.setSubtotal(orderDetail.getSubtotal());
		        details.setTax(orderDetail.getTax());
		
		Amount amount = new Amount();
		       amount.setCurrency("BRL");
		       amount.setTotal(orderDetail.getTotal());
		       amount.setDetails(details);
		
		Transaction transaction = new Transaction();
		            transaction.setAmount(amount);
		            transaction.setDescription(orderDetail.getProductName());
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		
		Item item = new Item();
		     item.setCurrency("BRL")
		         .setName(orderDetail.getProductName())
		         .setPrice(orderDetail.getSubtotal())
		         .setTax(orderDetail.getTax())
		         .setQuantity("1");
		     
		     items.add(item);
		     itemList.setItems(items);
		     transaction.setItemList(itemList);
	List<Transaction> listTranction = new ArrayList<Transaction>();
	listTranction.add(transaction);
	
		return listTranction;
	}
	
	
	private RedirectUrls getRedirectUrls() {
		RedirectUrls redirectUrls = new RedirectUrls();
		             redirectUrls.setCancelUrl("http://localhost:8080/ThePayPalExample/cancel.html");		            
		             redirectUrls.setReturnUrl("http://localhost:8080/ThePayPalExample/review_payment");
		
		return redirectUrls;
	}

	
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		   APIContext apiContext = new APIContext(client_id,client_secret,MODE);
		
		   return Payment.get(apiContext, paymentId);
		
	}
	
	private Payer getPayerInformation() {
		    Payer payer = new Payer();
		          payer.setPaymentMethod("PayPal");
		
		PayerInfo payerInfo = new PayerInfo();
		          payerInfo.setFirstName("Manoel")
		                   .setLastName("Ferreira")
		                   .setEmail("manrobfer@yahoo.com.br");
		                   
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	public Payment executePayment(String paymentId, String payerId)
	        throws PayPalRESTException {
		
	        PaymentExecution paymentExecution = new PaymentExecution();
	        
	        paymentExecution.setPayerId(payerId);
	 
	        Payment payment = new Payment().setId(paymentId);
	 
	        APIContext apiContext = new APIContext(client_id,client_secret,MODE);	        
	        
	 
	        return payment.execute(apiContext, paymentExecution);
	}

}
