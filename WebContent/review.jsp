<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revisar Pagamento</title>
</head>
<body>
     <div align="center">
        <h3> Revisar Iten Antes do Pagamento</h3>
        <form action="execute_payment" method="post">
        <table>
           <tr>
              <td>Detalhes da Transação </td>
              <td></td>
               
           </tr>           
           <tr>
              <td colspan="2"><b>Descrição da Transação</b> </td>
              <td>${transaction.desciption} </td>
           </tr>        
           <tr>
              <td>Subtotal </td>
              <td>${transaction.amount.details.subtotal} </td>
           </tr>
           <tr>
              <td>Frete </td>
              <td>${transaction.amount.details.shipping} </td>
           </tr>
           <tr>
              <td>Imposto </td>
              <td>${transaction.amount.tax} </td>
           </tr>
           
           <tr>
              <td>Total </td>
              <td>${transaction.amount.total} </td>
           </tr>
           <tr>
              <td>
                 <br/>
              </td>
            </tr>
            <tr>
              <td colspan="2"><b>Informação do Cliente</b> </td>
            </tr>
            <tr>
              <td>Nome </td>
              <td>${payer.firstName} </td>
           </tr>
           <tr>
              <td>Sobrenome </td>
              <td>${payer.lastLame} </td>
           </tr>
           <tr>
              <td>E-mail </td>
              <td>${payer.email} </td>
           </tr>
            <tr>
              <td>
                 <br/>
              </td>
            </tr>
            <tr>
              <td colspan="2"><b>Entrega</b> </td>
            </tr>
           <tr>
              <td>Recebedor </td>
              <td>${shippingAddress.recipientName} </td>
           </tr>
           <tr>
              <td>Linha 1 </td>
              <td>${shippingAddress.line1} </td>
           </tr>
           <tr>
              <td>Cidade </td>
              <td>${shippingAddress.city} </td>
           </tr>
           <tr>
              <td>Estado </td>
              <td>${shippingAddress.state} </td>
           </tr>
           <tr>
              <td>Código do País </td>
              <td>${shippingAddress.countryCode} </td>
           </tr>
           <tr>
              <td>Codigo Postal </td>
              <td>${shippingAddress.postalCode} </td>
           </tr>                      
           <tr>        
              <td><input type="submit" value="Pagar" /> </td>
           </tr>               
        
        </table>
        
        
        </form>
     
     
     </div>

</body>
</html>