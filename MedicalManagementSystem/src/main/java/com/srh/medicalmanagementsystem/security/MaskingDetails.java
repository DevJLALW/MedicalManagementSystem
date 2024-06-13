package com.srh.medicalmanagementsystem.security;

public class MaskingDetails {
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        int atIndex = email.indexOf("@");
        String maskedEmail = email.charAt(0) + "****" + email.charAt(atIndex - 1) + email.substring(atIndex);
        return maskedEmail;
    }

    public static String maskContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.length() < 4) {
            return contactNumber;
        }
        int length = contactNumber.length();
        String maskedContact = "*****" + contactNumber.substring(length - 3);
        return maskedContact;
    }
}
