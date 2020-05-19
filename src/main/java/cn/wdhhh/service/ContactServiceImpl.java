package cn.wdhhh.service;

import cn.wdhhh.NotFoundException;
import cn.wdhhh.dao.ContactRepository;
import cn.wdhhh.po.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> listContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        Contact c = contactRepository.findById(id).get();
        if (c == null) {
            throw new NotFoundException("信息不存在");
        }
        return contactRepository.save(contact);
    }
}
