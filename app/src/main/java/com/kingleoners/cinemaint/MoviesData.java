package com.kingleoners.cinemaint;

import android.os.Parcel;

import java.util.ArrayList;

public class MoviesData {

    public static String[][] data = new String[][]{
            {"Bridges","Bridge of Spies adalah film drama biografi Amerika Serikat tahun 2015 yang disutradarai oleh Steven Spielberg dan diproduseri oleh Steven Spielberg, Marc Platt dan Kristie Macosko Krieger. Naskah film ini ditulis oleh Matt Charman", "https://ia801405.us.archive.org/31/items/bridges_201909/bridges.png"},
            {"CharlieAngels","Charlie Angels adalah film komedi aksi Amerika tahun 2000 yang disutradarai oleh McG, dibintangi Cameron Diaz, Drew Barrymore, dan Lucy Liu", "https://ia801407.us.archive.org/30/items/charliesangels_201909/charliesangels.png"},
            {"TheDeadDontDie","The Dead Dont Die mengambil latar di sebuah kota kosong di Amerika tengah, bernama Centerville", "https://ia801509.us.archive.org/7/items/deaddontdie_201909/deaddontdie.png"},
            {"Geminiman","Gemini Man bercerita soal Henry Brogan (Will Smith), seorang pembunuh profesional menjadi sasaran pembunuhan lelaki misterius", "https://archive.org/download/geminiman/geminiman.png"},
            {"MySpy","My Spy merupakan film Amerika terbaru dengan genre action komedi yang disutradarai oleh Peter Segal dan diproduseri oleh Dave Bautista, Peter Segal, Chris Segal, Chris Bender, Jake Weiner, Jonathan Meisner, dan Gigi Pritzker", "https://archive.org/download/myspy/myspy.png"},
            {"ThePolarExpress","The Polar Express adalah sebuah film fantasi musikal Natal animasi komputer 3D Amerika Serikat tahun 2004 yang berdasarkan pada buku anak-anak tahun 1985 bernama sama karya Chris Van Allsburg", "https://ia801408.us.archive.org/32/items/polarexpress_201909/polarexpress.png"},
            {"Shaft","Detektif polisi Kota New York, John Shaft, melanjutkan misi pribadinya untuk memastikan anak seorang konglomerat real estate diseret ke pengadilan setelah melakukan pembunuhan yang bermotif rasial", "https://ia801504.us.archive.org/21/items/shaft_201909/shaft.png"},
            {"Zombieland","Zombieland merupakan sebuah film Amerika Serikat yang dirilis pada tahun 2009. Film yang disutradarai oleh Ruben Fleischer ini pemainnya antara lain ialah Jesse Eisenberg, Woody Harrelson, Emma Stone dan Abigail Breslin", "https://archive.org/download/zombiland/zombiland.png"},
            {"CaptainMarvel","Sekelompok hippies sedang bersantai di tengah padang gurun California yang panas. Tampaknya mereka tinggal di dalam sebuah trailer kecil disana. Alunan musik mengiringi aktivitas mereka di hari yang terik tersebut", "https://ia801509.us.archive.org/29/items/captainmarvel_201909/captainmarvel.png"},
            {"TheStars","Hazel yang hobi  menonton reality shows di tv ini ia tidak terlalu ingin mengikuti support group tersebut, karena dia merasa dirinya tidak mengalami depresi", "https://ia801407.us.archive.org/17/items/thestars_201909/thestars.png"},
    };

    public static ArrayList<Movie> getListData(){
        ArrayList<Movie> listMovie = new ArrayList<>();
        for (String[] bData : data){
            Movie movie = new Movie(Parcel.obtain());
            movie.setTitle(bData[0]);
            movie.setOverview(bData[1]);
            movie.setPoster_path(bData[2]);

            listMovie.add(movie);
        }

        return listMovie;
    }

}
