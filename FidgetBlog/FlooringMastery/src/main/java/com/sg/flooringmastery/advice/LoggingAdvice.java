/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringAuditDao;
import com.sg.flooringmastery.dto.Order;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author vincentsiciliano
 */
public class LoggingAdvice {
    
    FlooringAuditDao auditDao;

    public LoggingAdvice(FlooringAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp){
        Object[] args = jp.getArgs();
        String auditEntry=jp.getSignature().getName() + " : ";
        if("addOrder".equals(auditEntry)){
            auditEntry="ORDER ADDED: ";
        }else if("removeOrder".equals(auditEntry)){
            auditEntry="ORDER REMOVED";
        }else if("auditPreEdit".equals(auditEntry)){
            auditEntry="ORDER EDITED:";
        }
        
        for(Object currentArg : args){
            auditEntry+= "--" + (Order) currentArg;
        }
        auditDao.writeAuditToFile(auditEntry);
    }

}
