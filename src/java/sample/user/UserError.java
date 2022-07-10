/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Date;

/**
 *
 * @author Xqy
 */
public class UserError {

    private String userIDError;
    private String fullNameError;
    private String roleIDError;
    private String passwordError;
    private String confirmError;
    private String addressError;
    private Date birthdayError;
    private int phoneError;
    private String emailError;
    private boolean statusError;

    public UserError() {
        this.userIDError = "";
        this.fullNameError = "";
        this.roleIDError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.addressError = "";
        this.birthdayError = null;
        this.phoneError = 0;
        this.emailError = "";
        this.statusError = false;
    }

    public UserError(String userIDError, String fullNameError, String roleIDError, String passwordError, String confirmError, String addressError, Date birthdayError, int phoneError, String emailError, boolean statusError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.addressError = addressError;
        this.birthdayError = birthdayError;
        this.phoneError = phoneError;
        this.emailError = emailError;
        this.statusError = statusError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public Date getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(Date birthdayError) {
        this.birthdayError = birthdayError;
    }

    public int getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(int phoneError) {
        this.phoneError = phoneError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public boolean isStatusError() {
        return statusError;
    }

    public void setStatusError(boolean statusError) {
        this.statusError = statusError;
    }

}
