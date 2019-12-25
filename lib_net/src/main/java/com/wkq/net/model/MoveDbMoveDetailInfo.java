package com.wkq.net.model;

import androidx.databinding.BaseObservable;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/25
 * <p>
 * 简介:
 */
public class MoveDbMoveDetailInfo extends BaseObservable {


    /**
     * adult : false
     * backdrop_path : /5BwqwxMEjeFtdknRV792Svo0K1v.jpg
     * belongs_to_collection : null
     * budget : 87500000
     * genres : [{"id":878,"name":"科幻"},{"id":18,"name":"剧情"},{"id":53,"name":"惊悚"},{"id":12,"name":"冒险"},{"id":9648,"name":"悬疑"}]
     * homepage : https://www.foxmovies.com/movies/ad-astra
     * id : 419704
     * imdb_id : tt2935510
     * original_language : en
     * original_title : Ad Astra
     * overview : 　　布拉德·皮特和导演詹姆士·格雷合作的科幻新片[星际探索]，此次皮特饰演一名轻度自闭的航天工程师罗伊，他的父亲在20年前离开地球前往海王星，寻找外星智慧生物。不料任务失败，有去无回。而罗伊此次也要飞越太阳系，找到父亲，并查清当年失败的原因。
     * popularity : 449.066
     * poster_path : /kOBCDrMrWSGRDquMcW5vfKGxxB9.jpg
     * production_companies : [{"id":490,"logo_path":null,"name":"New Regency Productions","origin_country":""},{"id":79963,"logo_path":null,"name":"Keep Your Head","origin_country":""},{"id":73492,"logo_path":null,"name":"MadRiver Pictures","origin_country":""},{"id":81,"logo_path":"/8wOfUhA7vwU2gbPjQy7Vv3EiF0o.png","name":"Plan B Entertainment","origin_country":"US"},{"id":30666,"logo_path":"/g8LmDZVFWDRJW72sj0nAj1gh8ac.png","name":"RT Features","origin_country":"BR"},{"id":30148,"logo_path":null,"name":"Bona Film Group","origin_country":"CN"},{"id":22213,"logo_path":"/qx9K6bFWJupwde0xQDwOvXkOaL8.png","name":"TSG Entertainment","origin_country":"US"}]
     * production_countries : [{"iso_3166_1":"BR","name":"Brazil"},{"iso_3166_1":"CN","name":"China"},{"iso_3166_1":"US","name":"United States of America"}]
     * release_date : 2019-09-17
     * revenue : 127175922
     * runtime : 123
     * spoken_languages : [{"iso_639_1":"en","name":"English"},{"iso_639_1":"no","name":"Norsk"}]
     * status : Released
     * tagline : 终极真相  一触即发
     * title : 星际探索
     * video : false
     * vote_average : 6
     * vote_count : 1446
     */

    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private int budget;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private int vote_average;
    private int vote_count;
    private List<GenresBean> genres;
    private List<ProductionCompaniesBean> production_companies;
    private List<ProductionCountriesBean> production_countries;
    private List<SpokenLanguagesBean> spoken_languages;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Object getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Object belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<ProductionCompaniesBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountriesBean> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountriesBean> production_countries) {
        this.production_countries = production_countries;
    }

    public List<SpokenLanguagesBean> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguagesBean> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public static class GenresBean {
        /**
         * id : 878
         * name : 科幻
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductionCompaniesBean {
        /**
         * id : 490
         * logo_path : null
         * name : New Regency Productions
         * origin_country :
         */

        private int id;
        private Object logo_path;
        private String name;
        private String origin_country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(Object logo_path) {
            this.logo_path = logo_path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public static class ProductionCountriesBean {
        /**
         * iso_3166_1 : BR
         * name : Brazil
         */

        private String iso_3166_1;
        private String name;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SpokenLanguagesBean {
        /**
         * iso_639_1 : en
         * name : English
         */

        private String iso_639_1;
        private String name;

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
