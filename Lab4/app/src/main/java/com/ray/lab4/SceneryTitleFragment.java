package com.ray.lab4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2016/11/3.
 */
public class SceneryTitleFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView sceneryTitleListView;
    private List<Scenery> sceneryList;
    private SceneryAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sceneryList = getScenery();
        adapter = new SceneryAdapter(activity, R.layout.scenery_item, sceneryList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scenery_title_frag,container,false);
        sceneryTitleListView = (ListView)view.findViewById(R.id.sceneryTitleListView);
        sceneryTitleListView.setAdapter(adapter);
        sceneryTitleListView.setOnItemClickListener(this);
        return view;
    }

    private List<Scenery> getScenery() {
        List<Scenery> newSceneryList = new ArrayList<Scenery>();
        Scenery scenery1 = new Scenery("三峡","长江三峡西起重庆奉节县白帝城，东至湖北宜昌市南津关，全长193千米，沿途两岸奇峰陡立、峭壁对峙，自西向东依次为瞿塘峡、巫峡、西陵峡。",R.drawable.sanxia);
        Scenery scenery2 = new Scenery("秦始皇兵马俑","兵马俑是古代墓葬雕塑的一个类别。古代实行人殉，奴隶是奴隶主生前的附属品，奴隶主死后奴隶要作为殉葬品为奴隶主陪葬。兵马俑即制成兵马（战车、战马、士兵）形状的殉葬品。",R.drawable.binmayong);
        Scenery scenery3 = new Scenery("避暑山庄","承德避暑山庄又名“承德离宫”或“热河行宫”，位于河北省承德市中心北部，武烈河西岸一带狭长的谷地上，是清代皇帝夏天避暑和处理政务的场所。",R.drawable.bishushanzhuang);
        Scenery scenery4 = new Scenery("长城","长城（Great Wall）又称万里长城，是中国古代的军事防御工程。长城修筑的历史可上溯到西周时期，发生在首都镐京（今陕西西安）的著名的典故“烽火戏诸侯”就源于此。春秋战国时期列国争霸，互相防守，长城修筑进入第一个高潮，但此时修筑的长度都比较短。秦灭六国统一天下后，秦始皇连接和修缮战国长城，始有万里长城之称。明朝是最后一个大修长城的朝代，今天人们所看到的长城多是此时修筑。",R.drawable.changcheng);
        Scenery scenery5 = new Scenery("北京故宫","北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。",R.drawable.gugong);
        Scenery scenery6 = new Scenery("桂林山水","桂林山水是对桂林旅游资源的总称。[1]  典型的喀斯特地形构成了别具一格的桂林山水，桂林山水所指的范围很广，项目繁多。桂林山水山青、水秀、洞奇、石美，包括山、水、喀斯特岩洞、石刻等等。在桂林山水中又以漓江流经阳朔的那一段最为美丽，故而有“桂林山水甲天下，阳朔山水甲桂林”之美誉。",R.drawable.guiling);
        Scenery scenery7 = new Scenery("黄山","黄山位于安徽省南部黄山市境内，有72峰，主峰莲花峰海拔1864米，与光明顶、天都峰并称三大黄山主峰，为36大峰之一。黄山是安徽旅游的标志，是中国十大风景名胜唯一的山岳风光。",R.drawable.huangshan);
        Scenery scenery8 = new Scenery("日月潭","日月潭湖面海拔748米，常态面积为7.93㎞²（满水位时10㎞²），最大水深27米，湖周长约37千米，是台湾外来种生物最多的淡水湖泊之一。它以光华岛为界，北半湖形状如圆日，南半湖形状如弯月。",R.drawable.riyuetan);
        Scenery scenery9 = new Scenery("苏州古典园林","苏州园林始于春秋时期吴国建都姑苏时，形成于五代，成熟于宋代，兴旺鼎盛于明清。到清末苏州已有各色园林170多处，现保存完整的有60多处，对外开放的有19处，主要有沧浪亭、狮子林、拙政园、留园、网师园、怡园等园林。",R.drawable.suzhouyuanling);
        Scenery scenery0 = new Scenery("西湖","西湖三面环山，面积约6.39平方千米，东西宽约2.8千米，南北长约3.2千米，绕湖一周近15千米。湖中被孤山、白堤、苏堤、杨公堤分隔，按面积大小分别为外西湖、西里湖、北里湖、小南湖及岳湖等五片水面，苏堤、白堤越过湖面，小瀛洲、湖心亭、阮公墩三个小岛鼎立于外西湖湖心，夕照山的雷峰塔与宝石山的保俶塔隔湖相映，由此形成了“一山、二塔、三岛、三堤、五湖”的基本格局。",R.drawable.xihu);

        newSceneryList.add(scenery1);
        newSceneryList.add(scenery2);
        newSceneryList.add(scenery3);
        newSceneryList.add(scenery4);
        newSceneryList.add(scenery5);
        newSceneryList.add(scenery6);
        newSceneryList.add(scenery7);
        newSceneryList.add(scenery8);
        newSceneryList.add(scenery9);
        newSceneryList.add(scenery0);
        return newSceneryList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.sceneryContentLayout) != null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Scenery scenery = sceneryList.get(position);
        if(isTwoPane){
            SceneryContentFragment sceneryContentFragment = (SceneryContentFragment)getFragmentManager().findFragmentById(R.id.sceneryContentFragment);
            sceneryContentFragment.refresh(scenery.getTitle(),scenery.getContent(),scenery.getImage());
        }
        else{
            SceneryContentActivity.actionStart(getActivity(),scenery.getTitle(),scenery.getContent(),scenery.getImage());
        }
    }
}
