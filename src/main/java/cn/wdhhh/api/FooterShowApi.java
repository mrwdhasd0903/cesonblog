package cn.wdhhh.api;

import cn.wdhhh.po.Contact;
import cn.wdhhh.po.Links;
import cn.wdhhh.service.ContactService;
import cn.wdhhh.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class FooterShowApi {

    @Autowired
    private ContactService contactService;
    @Autowired
    private LinksService linksService;
    /**
     * 获取联系\支付等信息
     * @return
     */
    @GetMapping("/contacts")
    public List<Contact> listContact(){
        return contactService.listContact();
    }

    @GetMapping("/links")
    public List<Links> listLink(){
        return linksService.listLinks();
    }

}
