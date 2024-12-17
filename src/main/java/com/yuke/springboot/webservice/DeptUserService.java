package com.yuke.springboot.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(targetNamespace = "http://GetUserInfoService.com/")
@BindingType(value = SOAPBinding.SOAP11HTTP_BINDING)
public interface DeptUserService {
    /**
     * 更新用户信息
     *
     * @param model
     * @param userInfo
     * @return
     */
    @WebMethod(operationName = "UpdateUserInfo", action = "urn:anonOutInOp")
    public String updateUserInfo(@WebParam(name = "Mode", targetNamespace = "http://GetUserInfoService.com/") String model,
                                 @WebParam(name = "UserInfo", targetNamespace = "http://GetUserInfoService.com/") String userInfo);

    /**
     * 更新组织信息
     *
     * @param model
     * @param deptInfo
     * @return
     */
    @WebMethod(operationName = "UpdateBranchInfo", action = "urn:anonOutInOp")
    public String updateDeptInfo(@WebParam(name = "Mode", targetNamespace = "http://GetUserInfoService.com/") String model,
                                 @WebParam(name = "UserInfo", targetNamespace = "http://GetUserInfoService.com/") String deptInfo);
}
