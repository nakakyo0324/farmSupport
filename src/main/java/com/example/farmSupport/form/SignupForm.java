package com.example.farmSupport.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupForm {
    @NotBlank(message = "ユーザ名は必須です")
    @Size(min=4,max=20,message="ユーザ名は4文字以上20文字以内で入力してください")
    private String username;

    @NotBlank(message="パスワード1は必須です")
    private String password1;

    @NotBlank(message="パスワード2は必須です")
    private String password2;

    @NotBlank(message="メールアドレスは必須です")
    @Email(message="メールアドレスの形式で入力してください")
    private String email;

}
