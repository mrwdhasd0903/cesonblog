package cn.wdhhh.dao;

import cn.wdhhh.po.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
