package com.micb2b.purchasing.mapper;

import com.micb2b.purchasing.domain.Menu;
import com.micb2b.purchasing.dto.MenuDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-07T14:25:06+0800",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setComponent( dto.getComponent() );
        menu.setCreateTime( dto.getCreateTime() );
        menu.setIFrame( dto.getIFrame() );
        menu.setIcon( dto.getIcon() );
        menu.setId( dto.getId() );
        menu.setName( dto.getName() );
        menu.setPath( dto.getPath() );
        menu.setPid( dto.getPid() );
        menu.setSort( dto.getSort() );

        return menu;
    }

    @Override
    public MenuDTO toDto(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setComponent( entity.getComponent() );
        menuDTO.setCreateTime( entity.getCreateTime() );
        menuDTO.setIFrame( entity.getIFrame() );
        menuDTO.setIcon( entity.getIcon() );
        menuDTO.setId( entity.getId() );
        menuDTO.setName( entity.getName() );
        menuDTO.setPath( entity.getPath() );
        menuDTO.setPid( entity.getPid() );
        menuDTO.setSort( entity.getSort() );

        return menuDTO;
    }

    @Override
    public List<Menu> toEntity(List<MenuDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>();
        for ( MenuDTO menuDTO : dtoList ) {
            list.add( toEntity( menuDTO ) );
        }

        return list;
    }

    @Override
    public List<MenuDTO> toDto(List<Menu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuDTO> list = new ArrayList<MenuDTO>();
        for ( Menu menu : entityList ) {
            list.add( toDto( menu ) );
        }

        return list;
    }
}
