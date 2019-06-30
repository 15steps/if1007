package br.ufpe.cin.service;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderStatus;

public class PaymentService {

    public static Order process(Order order) {
        if (order != null) {
            CardInfo card = order.getCardInfo();
            boolean validate = luhnCheck(card);
            order.setStatus( validate ? OrderStatus.PAYMENT_ACCEPTED : OrderStatus.PAYMENT_DENIED );
        }
        return  order;
    }

    private static String processNumber(String number) {
        return number.replaceAll("[^0-9]","");
    }

    private static boolean luhnCheck(CardInfo cardInfo) {
        int sum = 0;
        boolean alternate = false;
        String cardNumber = processNumber(cardInfo.getCardNumber());

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
