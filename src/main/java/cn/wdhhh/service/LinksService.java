package cn.wdhhh.service;

import cn.wdhhh.po.Links;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LinksService {

    Links getLink(Long id);

    List<Links> listLinks();

    Page<Links> listLinks(Pageable pageable);

    Links saveLinks(Links links);

    Links updateLinks(Long id, Links links);

    void deleteLinks(Long id);
}
