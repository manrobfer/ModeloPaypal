package com.pay.pal.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/review_payment")
public class ReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	
	
    public ReviewPaymentServlet() {
        super();
    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String paymentId = request.getParameter("paymentId");
		String payerId   = request.getParameter("PayerID");			
		
		try {
			
		    PaymentServices paymentServices = new PaymentServices();
		    Payment payment = paymentServices.getPaymentDetails(paymentId);
		    
		    PayerInfo payerInfo = payment.getPayer().getPayerInfo();
		    Transaction transaction = payment.getTransactions().get(0);
		    
		    ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
		    
		    request.setAttribute("payer",payerInfo);
		    request.setAttribute("transaction",transaction);
		    request.setAttribute("shippingAddress",shippingAddress);
		    
		    String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;		    
		    
		    
		    request.getRequestDispatcher(url).forward(request,response);
		    
		}catch(PayPalRESTException rex) {
			
			rex.printStackTrace();	
			
			request.setAttribute("errorMessage","Informações de pagamento inválidas");
			request.getRequestDispatcher("error.jsp").forward(request,response);			
		}
		
		
		response.getWriter().append("Servido em: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
