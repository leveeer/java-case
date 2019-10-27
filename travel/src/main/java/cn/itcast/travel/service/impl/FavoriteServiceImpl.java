package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);
        //如果对象有值则为true,沒值则为false
        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid),uid);
    }
}
