
package com.spring.Model;

import com.andromeda.commons.model.BaseModel;

public class Book extends BaseModel
{

private String bookname;
private int isbn;
private String author;        
private int code;
private String publishdate;
private int noofcopies;
public String getBookname() {
return bookname;
}

public void setBookname(String bookname) {
this.bookname = bookname;
}

public int getIsbn() {
return isbn;
}

public void setIsbn(int isbn) {
this.isbn = isbn;
}

public String getAuthor() {
return author;
}

public void setAuthor(String author) {
this.author = author;
}

public int getCode() {
return code;
}

public void setCode(int code) {
this.code = code;
}

public String getPublishdate() {
return publishdate;
}

public void setPublishdate(String publishdate) {
this.publishdate = publishdate;
}


public int getNoofcopies() {
return noofcopies;
}

public void setNoofcopies(int noofcopies) {
this.noofcopies = noofcopies;
}
               
}