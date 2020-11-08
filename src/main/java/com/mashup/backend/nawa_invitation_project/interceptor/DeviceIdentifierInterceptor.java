package com.mashup.backend.nawa_invitation_project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DeviceIdentifierInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception {
    String deviceIdentifier = request.getHeader("deviceIdentifier");
    if(deviceIdentifier.equals("") || deviceIdentifier == null) {
      response.sendError(400, "Set device identifier.");
      return false;
    }
    return true;
  }
}
