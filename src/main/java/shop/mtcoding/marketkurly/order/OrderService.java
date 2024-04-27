package shop.mtcoding.marketkurly.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
    String current = dateFormat.format(now);
    Integer numberCode = 1000000;

    // private String orderNumber = current + numberCode;

}
