package com.kkoalla.kkoallaspring.dto.response;

import lombok.Data;

@Data
public class KakaoAccount {
    public Boolean email_needs_agreement;
    public Boolean is_email_valid;
    public String email;
}
