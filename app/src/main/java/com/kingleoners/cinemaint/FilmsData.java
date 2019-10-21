package com.kingleoners.cinemaint;

import android.os.Parcel;

import java.util.ArrayList;

public class FilmsData {
    public static String[][] data = new String[][]{
            {"Hitam Putih","Hitam Putih adalah sebuah acara gelar wicara yang ditayangkan Trans7. Acara ini dibawakan oleh mentalist Indonesia, Deddy Corbuzier. Setiap acaranya menyampaikan tema-tema inspiratif yang dibawakan secara santai.", "https://ia801503.us.archive.org/1/items/hitamputih_201909/hitamputih.jpg"},
            {"Indonesian Idol","Indonesian Idol, berjudul Indonesian Idol X: Home of the Idols untuk musim kesepuluh, adalah suatu ajang pencarian bakat yang diadopsi dari Pop Idol dengan sponsor dari FremantleMedia yang bekerjasama dengan RCTI. Ajang ini merupakan pencarian idola di bidang tarik suara.", "https://archive.org/download/indonesianidol/indonesianidol.png"},
            {"Lapan Anam","(Delapan Enam) adalah program acara realitas yang diproduksi secara kerjasama antara NET. dan Kepolisian Negara Republik Indonesia mengenai keseharian beberapa anggota polisi", "https://archive.org/download/lapananam/lapananam.png"},
            {"Mancing Mania","Sang presenter memulai petualangan memancing ke berbagai destinasi terkenal, dan berbagi tips tentang cara menangkap ikan, sambil menikmati pemandangan alam yang menawan.", "https://archive.org/download/mancingmania/mancingmania.png"},
            {"Master Chef","MasterChef Indonesia adalah acara televisi pencarian bakat memasak yang diadopsi dari acara MasterChef yang tayang di stasiun televisi RCTI.", "https://archive.org/download/masterchef/masterchef.png"},
            {"Moto GP","Grand Prix Sepeda Motor Indonesia adalah acara balap motor yang pernah menjadi bagian dari Kejuaraan Dunia Grand Prix Sepeda Motor.", "https://ia801503.us.archive.org/2/items/motogp_201909/motogp.png"},
            {"One Stop Football","One Stop Football adalah sebuah acara buletin sepak bola di Trans 7 yang ditayangkan setiap hari Sabtu dan Minggu.", "https://archive.org/download/onestopfootball/onestopfootball.png"},
            {"Opera Van Java","Opera Van Java adalah acara komedi yang ditayangkan di Trans7. Ide acaranya adalah pertunjukan wayang orang versi modern. Dilakonkan oleh komedian ternama seperti Parto Patrio, Denny Cagur, Mpok Alpa, Rina Nose, Adul, Anwar Sanjaya Pigano, Cesar YKS serta Ferdians Setiadi berperang tawa bersama para bintang tamu.", "https://archive.org/download/operavanjava/operavanjava.png"},
            {"Silet","Silet adalah sebuah program informasi hiburan yang memuat kehidupan para pesohor (selebritis) yang ditayangkan oleh stasiun RCTI. Silet mengungkapkan fakta-fakta berupa kejadian seputar misteri, kehidupan para selebriti, kehidupan para politikus, atau tragedi yang mengguncang kehidupan.", "https://archive.org/download/silet/silet.png"},
            {"Spongebob Squarepants","SpongeBob SquarePants adalah sebuah serial animasi yang paling terpopuler di Nickelodeon. Pada awalnya serial kartun animasi ini ditayangkan pada tahun 1999 di Amerika Serikat dan dicipta oleh Stephen Hillenburg, seorang animator dan ahli biologi laut, dan diterbitkan oleh perusahaannya, United Plankton Pictures Inc.", "https://ia801503.us.archive.org/27/items/spongebob_20190916/spongebob.png"},
    };

    public static ArrayList<Film> getListData(){
        ArrayList<Film> listFilm = new ArrayList<>();
        for (String[] aData : data){
            Film film = new Film(Parcel.obtain());
            film.setName(aData[0]);
            film.setOverview(aData[1]);
            film.setPoster_path(aData[2]);

            listFilm.add(film);
        }

        return listFilm;
    }


}
