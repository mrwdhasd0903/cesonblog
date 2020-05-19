package cn.wdhhh.api.admin;


import cn.wdhhh.api.FooterShowApi;
import cn.wdhhh.po.Contact;
import cn.wdhhh.po.Links;
import cn.wdhhh.service.ContactService;
import cn.wdhhh.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class FooterApi {
    @Autowired
    private ContactService contactService;
    @Autowired
    private LinksService linksService;

    @GetMapping("/contacts")
    public List<Contact> listContact() {
        return contactService.listContact();
    }

    /**
     * 添加与修改集成 联系信息
     *
     * @param contact
     * @return
     */
    @PostMapping("/contacts")
    public int contacts(Contact contact) {
        Contact c;
        //id是否存在
        if (contact.getId() == null) {
            //不存在->保存
            c = contactService.saveContact(contact);
        } else {
            //存在->更新
            c = contactService.updateContact(contact.getId(), contact);
        }
        if (c == null) {
            //失败
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 不分页返回全部链接
     *
     * @return
     */
    @GetMapping("/links")
    public List<Links> listLink() {
        return linksService.listLinks();
    }

    @GetMapping("/linksByPage")
    public Page<Links> linksByPage(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC)
                                           Pageable pageable) {
        return linksService.listLinks(pageable);
    }

    /**
     * 返回一个links对象
     *
     * @param id
     * @return
     */
    @GetMapping("/link")
    public Links link(Long id) {
        return linksService.getLink(id);
    }

    /**
     * 添加和修改集成 外链信息
     *
     * @param links
     * @return
     */
    @PostMapping("/links")
    public int post(Links links) {
        Links l;
        //id是否存在
        if (links.getId() == null) {
            //不存在->保存
            l = linksService.saveLinks(links);
        } else {
            //存在->更新
            l = linksService.updateLinks(links.getId(), links);
        }
        if (l == null) {
            //失败
            return 1;
        } else {
            return 0;
        }
    }

    @GetMapping("/dlinks")
    public int delete(Long id) {
        try {
            linksService.deleteLinks(id);
            //成功
            return 0;
        } catch (Exception e) {
            //失败
            return 1;
        }
    }
}
