package wkq.com.lib_move.site;

import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wkq.com.lib_move.model.MoveTopInfo;
import wkq.com.lib_move.utlis.MoveDataCallBack;
import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.model.MoveInfo;
import wkq.com.lib_move.utlis.MovieNetUtil;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-02
 * <p>
 * 用途:
 */


public class MTimeSite extends Site {
    public static String baseUrl = "http://video.mtime.com/";
    private static String pngfen;

    private static String moveStarring = null;
    private static String moveName = null;
    private static String moveHref = null;
    private static String moveScore = null;

    private static String moveImg = null;
    private static int pageNum = 0;

    public static void getHomeContent(MoveDataCallBack callBack) {


        Observable.create((ObservableOnSubscribe<List<MTimeHomeBean>>) emitter -> {
            String html = MovieNetUtil.getHtml(baseUrl, "UTF-8");
            Element element = Jsoup.parse(html).select("._2dj0d").first();
            List<Element> elements = element.children();
            List<MTimeHomeBean> beans = new ArrayList<>();

            for (Element bean : elements) {
                MTimeHomeBean mTimeHomeBean = new MTimeHomeBean();
                String title = bean.select("._17I1g").first().text();
                if (!TextUtils.isEmpty(title)) {
                    if (title.startsWith("更多")) {
                        title = title.replace("更多", "");

                    }

                    mTimeHomeBean.setTitleName(title);
                }

                List<Element> moves = bean.select("._1VCpH ._3umdT .d2fWI");
                List<MoveInfo> moveInfos = new ArrayList<>();
                for (Element move : moves) {
                    MoveInfo info = new MoveInfo();
                    String href = move.select("a").first().attr("href");
                    if (!TextUtils.isEmpty(href)) {
                        href = getMoveId(href);
                    }


                    String name = move.select("img").first().attr("title");
                    String img = move.select("img").first().attr("src");
                    if (move.select("._3_ru_").first() != null) {
                        pngfen = move.select("._3_ru_").first().text();
                    }
                    info.setMoveName(name);
                    info.setMoveCover(img);
                    info.setMoveHref(href);
                    info.setMoveScore(pngfen);
                    moveInfos.add(info);
                }
                if (moveInfos.size() > 0) {
                    mTimeHomeBean.setMoveInfos(moveInfos);
                    beans.add(mTimeHomeBean);
                }

                //首页的数据
            }
            emitter.onNext(beans);

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MTimeHomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<MTimeHomeBean> datas) {
                        callBack.onSuccess(datas);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail("解析异常");
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public static void getMTimeTop(MoveDataCallBack callBack) {


        Observable.create((ObservableOnSubscribe<List<MTimeHomeBean>>) emitter -> {
//            String html = MovieNetUtil.getHtml("http://video.mtime.com/search", "UTF-8");
            String html = MovieNetUtil.getHtml("http://video.mtime.com/search/?h=movie&s=1&p=1", "UTF-8");
//            Document element = Jsoup.parse(html);

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MTimeHomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<MTimeHomeBean> datas) {
                        callBack.onSuccess(datas);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail("解析异常");
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public static <T> void getMoveDetail(String moveUrl, MoveDataCallBack<T> moveDataCallBack) {

        Observable.create((ObservableOnSubscribe<T>) emitter -> {
            String html = MovieNetUtil.getHtml(moveUrl, "UTF-8");
            if (TextUtils.isEmpty(html) || Jsoup.parse(html).select(".xXnBC") == null || Jsoup.parse(html).select(".xXnBC").first() == null) {
                emitter.onError(new Throwable("暂无数据"));
                return;
            }

            Element element = Jsoup.parse(html).select(".xXnBC").first();

            if (Jsoup.parse(html).select(".total") != null && Jsoup.parse(html).select(".total").first() != null) {
                String pages = Jsoup.parse(html).select(".total").first().text();

                pageNum = getPages(pages);
            }


            if (element.children() == null || element.children().first() == null || element.children().first().children() == null) {
                emitter.onError(new Throwable("暂无数据"));
                return;
            }
            List<Element> elements = element.children().first().children();


            List<MoveTopInfo> beans = new ArrayList<>();

            for (Element bean : elements) {
                MoveTopInfo info = new MoveTopInfo();

                if (bean.select("._18_7o").select("img") != null && bean.select("._18_7o").select("img").first() != null)
                    moveName = bean.select("._18_7o").select("img").first().attr("alt");
                if (bean.select("._18_7o").select("a") != null && bean.select("._18_7o").select("a").first() != null)
                    moveHref = bean.select("._18_7o").select("a").first().attr("href");
                if (bean.select("._18_7o").select("img") != null && bean.select("._18_7o").select("img").first() != null)
                    moveImg = bean.select("._18_7o").select("img").first().attr("src");
                if (bean.select("._18_7o").select("span") != null && bean.select("._18_7o").select("span").first() != null)
                    moveScore = bean.select("._18_7o").select("span").first().text();
                if (bean.select("p") != null && bean.select("p").first() != null)
                    moveStarring = bean.select("p").first().text();

                info.setMoveName(moveName);

                info.setMoveCover(moveImg);

                info.setMoveHref(getMoveId(moveHref));
                info.setMoveScore(moveScore);
                info.setMoveStarring(moveStarring);
                info.setPageNum(pageNum);
                beans.add(info);
                //首页的数据
            }
            if (beans == null || beans.size() == 0) {
                emitter.onError(new Throwable("暂无数据"));
            } else {
                emitter.onNext((T) beans);
            }


        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(T datas) {
                        moveDataCallBack.onSuccess((T) datas);

                    }

                    @Override
                    public void onError(Throwable e) {
                        moveDataCallBack.onFail("解析异常");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static String getMoveId(String url) {
        String moveId = Pattern.compile("[^0-9]").matcher(url).replaceAll("");

        return moveId;
    }

    public static int getPages(String url) {
        int pageNum = 0;
        String pages = Pattern.compile("[^0-9]").matcher(url).replaceAll("");

        if (!TextUtils.isEmpty(pages)) {
            pageNum = Integer.parseInt(pages);
        }

        return pageNum;
    }
}
