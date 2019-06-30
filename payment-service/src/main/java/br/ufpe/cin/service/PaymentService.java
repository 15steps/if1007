package br.ufpe.cin.service;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentService {

    private RedisTemplate<String, Boolean> redisTemplate;

    public PaymentService(RedisTemplate<String, Boolean> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Order process(Order order) {

        CardInfo card = order.getCardInfo();
        String number = processNumber(card.getCardNumber());
        Boolean valid = redisTemplate.opsForValue().get(number);

        if (valid == null) {
            boolean checked = luhnCheck(card) && dateCheck(card);
            setStatus(order, checked);
            redisTemplate.opsForValue().set(number, checked);
        }else{
            setStatus(order, valid);
        }
        return  order;
    }

    private void setStatus(Order order, boolean valid) {
        order.setStatus( valid ? OrderStatus.PAYMENT_ACCEPTED : OrderStatus.PAYMENT_DENIED );
    }

    public String processNumber(String number) {
        return number.replaceAll("[^0-9]","");
    }

    public String processDate(String date) {
        return date.replaceAll("[-.,;|/\\s]", "/");
    }

    public boolean dateCheck(CardInfo cardInfo) {
        try {
            String dateString = processDate(cardInfo.getExp());
            Date date = new SimpleDateFormat("MM/yyyy").parse(dateString);
            return date.after(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean luhnCheck(CardInfo cardInfo) {
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
