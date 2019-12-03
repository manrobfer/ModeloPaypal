package com.pay.pal.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.base.rest.PayPalRESTException;

/**
 * Servlet implementation class AuthorizaPaymentServlet
 */
@WebServlet("/Authorize_Payment")
public class AuthorizaPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public AuthorizaPaymentServlet() {
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("product");
		String subtotal = request.getParameter("subtotal");
		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String total = request.getParameter("total");
		
		
		
		OrderDetail orderDetail = new OrderDetail(product,subtotal,shipping,tax,total);
		
		
		try {
		    PaymentServices paymentService = new PaymentServices();
		    String approvalLink = paymentService.authorizaPayment(orderDetail);		    
		   
		    
		    response.sendRedirect(approvalLink);
		    
		}catch(PayPalRESTException prex) {
			
			prex.printStackTrace();
			request.setAttribute("errorMessage","Informações de pagamento inválidas");
			request.getRequestDispatcher("error.jsp").forward(request,response);
			
		}
	}

}
