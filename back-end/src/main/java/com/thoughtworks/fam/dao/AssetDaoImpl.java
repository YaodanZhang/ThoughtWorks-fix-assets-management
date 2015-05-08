package com.thoughtworks.fam.dao;

import com.thoughtworks.fam.model.Asset;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by SiyuYan on 15-5-2.
 */
@Repository
public class AssetDaoImpl implements AssetDao {
    List<Asset> AllAssetList = Arrays.asList(
            new Asset("Nokia", "17006011", "2014-04-20", "Mobile", "yansiyu"),
            new Asset("MBP", "17005800", "2014-04-28", "lap top", "yansiyu"),
            new Asset("Screen", "17006036", "2014-06-16", "Others", "yansiyu"),
            new Asset("Nokia", "17006011", "2014-04-20", "Mobile", "lvjing"),
            new Asset("MBP", "17005800", "2014-04-28", "lap top", "lvjing"),
            new Asset("Screen", "17006036", "2014-06-16", "Others", "lvjing"),
            new Asset("Nokia", "17006011", "2014-04-20", "Mobile", "jtao"),
            new Asset("MBP", "17005800", "2014-04-28", "lap top", "jtao"),
            new Asset("Screen", "17006036", "2014-06-16", "Others", "jtao")
    );

    @Override
    public List<Asset> getAssets(String ownerName) {
        List<Asset> ownerAssets = new ArrayList<Asset>();
        for (Asset asset : AllAssetList) {
            if (asset.getOwnerName().equals(ownerName))
                ownerAssets.add(asset);
        }
        return ownerAssets;
    }

    @Override
    public List<Asset> getOthersAssets(String ownerName) {
        List<Asset> othersAssets = new ArrayList<Asset>();
        for (Asset asset : AllAssetList) {
            if (asset.getOwnerName().equals(ownerName))
                continue;
            else othersAssets.add(asset);
        }
        return othersAssets;
    }
}
