package cn.mth.mthcartprovider.service;

import org.apache.http.client.methods.HttpRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GetCookies {

    String getCookies(HttpServletRequest request, HttpServletResponse response);

}
