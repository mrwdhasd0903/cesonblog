package cn.wdhhh.service;

import cn.wdhhh.po.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> listContact();

    Contact saveContact(Contact contact);

    Contact updateContact(Long id, Contact contact);
}
