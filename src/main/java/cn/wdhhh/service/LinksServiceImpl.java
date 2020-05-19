package cn.wdhhh.service;

import cn.wdhhh.NotFoundException;
import cn.wdhhh.dao.LinksRepository;
import cn.wdhhh.po.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksRepository linksRepository;

    @Override
    public Links getLink(Long id) {
        return linksRepository.findById(id).get();
    }

    @Override
    public List<Links> listLinks() {
        return linksRepository.findAll();
    }

    @Override
    public Page<Links> listLinks(Pageable pageable) {
        return linksRepository.findAll(pageable);
    }

    @Override
    public Links saveLinks(Links links) {
        return linksRepository.save(links);
    }

    @Override
    public Links updateLinks(Long id, Links links) {
        Links l = linksRepository.findById(id).get();
        if (l == null) {
            throw new NotFoundException("该外链id不存在");
        }
        return linksRepository.save(links);
    }

    @Override
    public void deleteLinks(Long id) {
        linksRepository.deleteById(id);
    }
}
