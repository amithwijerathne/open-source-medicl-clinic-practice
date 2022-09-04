package lk.ijse.dep9.controller;

import lk.ijse.dep9.clinic.security.SecurityContexHolder;

public class AdminFormController {
    public void initialize(){
        System.out.println(SecurityContexHolder.getPrinciple());
    }
}
