package com.training.videoteca.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityPojo implements Serializable {
    private static final long serialVersionUID = 4078496449082663146L;

    private String username;
    private String password;
}
