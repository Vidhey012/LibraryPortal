package com.spring.Model;

import com.andromeda.commons.model.BaseModel;

public class Contact extends BaseModel
{
private String name;
private String email;
private String message;
public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}
