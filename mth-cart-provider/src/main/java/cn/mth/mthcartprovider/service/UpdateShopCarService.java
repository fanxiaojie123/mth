package cn.mth.mthcartprovider.service;

import org.apache.http.protocol.HTTP;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改数量
 */
public interface UpdateShopCarService {

    String updateShopcar(@RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response, @RequestParam Long shoPid, @RequestParam Integer num)throws IOException;;
}
