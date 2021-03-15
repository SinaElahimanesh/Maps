package com.guardian.googlemaps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
//import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Geocoder geo = new Geocoder(MapsActivity.this, new Locale("fa"));

        String languageToLoad = "fa_";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

//        Polyline polyline;

//        String string = "{\"code\":\"Ok\",\"waypoints\":[{\"hint\":\"LowHhjqMB4YAAAAAXAAAANoAAAAOAwAAAAAAAPvQf0K9FhhDub8HRAAAAABcAAAA2gAAAA4DAAB2GQEAzPkgAndcEAPi-CACN14QAwMA3wRJZz7V\",\"distance\":52.433302,\"location\":[35.715532,51.403895],\"name\":\"Парижская улица\"},{\"hint\":\"kdgMhp7YDIYAAAAAFAAAAO4HAAB0AAAAAAAAAEmXrUH0_QxFl7L_QgAAAAAUAAAA7gcAAHQAAAB2GQEAW4EiAlAXCgNUwyICvhEKAyIAXwBJZz7V\",\"distance\":1196.805929,\"location\":[35.815771,50.992976],\"name\":\"\"}],\"routes\":[{\"legs\":[{\"steps\":[{\"intersections\":[{\"out\":0,\"entry\":[true],\"location\":[35.715532,51.403895],\"bearings\":[250]}],\"driving_side\":\"right\",\"geometry\":{\"coordinates\":[[35.715532,51.403895],[35.714395,51.403632],[35.713927,51.40359],[35.713432,51.40364],[35.713379,51.40372]],\"type\":\"LineString\"},\"duration\":23.7,\"distance\":161.4,\"name\":\"Парижская улица\",\"weight\":23.7,\"mode\":\"driving\",\"maneuver\":{\"bearing_after\":250,\"location\":[35.715532,51.403895],\"type\":\"depart\",\"bearing_before\":0,\"modifier\":\"right\"}},{\"intersections\":[{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.713379,51.40372],\"bearings\":[165,285,330]}],\"driving_side\":\"right\",\"geometry\":{\"coordinates\":[[35.713379,51.40372],[35.712823,51.403859],[35.712184,51.403811],[35.71134,51.404018],[35.710659,51.403944],[35.709671,51.403519],[35.709299,51.403369]],\"type\":\"LineString\"},\"duration\":44.9,\"distance\":311.1,\"name\":\"\",\"weight\":44.9,\"mode\":\"driving\",\"maneuver\":{\"bearing_after\":291,\"location\":[35.713379,51.40372],\"type\":\"turn\",\"bearing_before\":337,\"modifier\":\"left\"}},{\"intersections\":[{\"out\":2,\"in\":1,\"entry\":[true,false,true],\"location\":[35.709299,51.403369],\"bearings\":[30,60,210]}],\"driving_side\":\"right\",\"geometry\":{\"coordinates\":[[35.709299,51.403369],[35.708632,51.402767],[35.707747,51.402515],[35.706016,51.401587],[35.704708,51.400842],[35.703306,51.400114],[35.703112,51.399823],[35.702893,51.399688],[35.702574,51.399632],[35.701953,51.39973]],\"type\":\"LineString\"},\"duration\":105,\"distance\":680.6,\"name\":\"улица Захаровка\",\"weight\":105,\"mode\":\"driving\",\"maneuver\":{\"bearing_after\":213,\"location\":[35.709299,51.403369],\"type\":\"turn\",\"bearing_before\":236,\"modifier\":\"straight\"}},{\"intersections\":[{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.701953,51.39973],\"bearings\":[105,165,345]},{\"out\":0,\"in\":2,\"entry\":[true,true,false],\"location\":[35.702746,51.398279],\"bearings\":[150,210,345]},{\"out\":0,\"in\":2,\"entry\":[true,true,false],\"location\":[35.705049,51.395602],\"bearings\":[150,210,330]},{\"out\":0,\"in\":2,\"entry\":[true,true,false],\"location\":[35.708825,51.391309],\"bearings\":[150,240,330]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.712003,51.387851],\"bearings\":[45,120,315]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.726882,51.376671],\"bearings\":[60,165,345]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.725181,51.366182],\"bearings\":[15,90,195]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.722177,51.355301],\"bearings\":[15,75,180,255]},{\"out\":1,\"in\":3,\"entry\":[true,true,true,false],\"location\":[35.729015,51.330872],\"bearings\":[60,135,285,330]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.731879,51.32876],\"bearings\":[60,180,315]},{\"out\":2,\"in\":1,\"entry\":[true,false,true],\"location\":[35.731162,51.326671],\"bearings\":[0,30,210]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.730797,51.326215],\"bearings\":[30,120,225]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.725977,51.323334],\"bearings\":[60,225,330]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":167,\"location\":[35.701953,51.39973],\"type\":\"turn\",\"bearing_before\":284,\"modifier\":\"left\"},\"duration\":1407.5,\"distance\":9767,\"name\":\"улица Перькова\",\"geometry\":{\"coordinates\":[[35.701953,51.39973],[35.702127,51.399213],[35.702746,51.398279],[35.703945,51.396716],[35.705049,51.395602],[35.706468,51.394095],[35.707641,51.392725],[35.708825,51.391309],[35.71146,51.388153],[35.712003,51.387851],[35.718622,51.384908],[35.725169,51.382053],[35.725698,51.381593],[35.726882,51.376671],[35.727019,51.376104],[35.727451,51.373443],[35.725181,51.366182],[35.722177,51.355301],[35.722865,51.350178],[35.723691,51.344966],[35.724942,51.340328],[35.726616,51.335928],[35.727679,51.333301],[35.728245,51.331835],[35.728579,51.331228],[35.729015,51.330872],[35.729952,51.330263],[35.730761,51.329656],[35.731879,51.32876],[35.731891,51.328604],[35.731751,51.327898],[35.731502,51.327168],[35.731162,51.326671],[35.730797,51.326215],[35.730171,51.325729],[35.728895,51.324818],[35.727862,51.324172],[35.726622,51.323621],[35.725977,51.323334],[35.72582,51.323264],[35.725109,51.322877],[35.724355,51.322262],[35.723395,51.321453]],\"type\":\"LineString\"},\"ref\":\"38Н-079\",\"weight\":1407.5,\"mode\":\"driving\"},{\"intersections\":[{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.723395,51.321453],\"bearings\":[30,120,300]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":292,\"location\":[35.723395,51.321453],\"type\":\"end of road\",\"bearing_before\":215,\"modifier\":\"right\"},\"duration\":52.3,\"distance\":501.9,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.723395,51.321453],[35.71937,51.322537],[35.718089,51.322934],[35.716951,51.323452]],\"type\":\"LineString\"},\"ref\":\"38Н-091\",\"weight\":52.3,\"mode\":\"driving\"},{\"intersections\":[{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.716951,51.323452],\"bearings\":[120,180,315]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.715859,51.310799],\"bearings\":[0,180,225]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.732645,51.288868],\"bearings\":[75,135,315]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.736317,51.279338],\"bearings\":[75,165,345]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.753565,51.220686],\"bearings\":[26,31,172]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.754674,51.215611],\"bearings\":[0,105,180]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":182,\"location\":[35.716951,51.323452],\"type\":\"turn\",\"bearing_before\":305,\"modifier\":\"sharp left\"},\"duration\":1861.2,\"distance\":14947.8,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.716951,51.323452],[35.715859,51.310799],[35.715145,51.302876],[35.715145,51.301696],[35.715393,51.301106],[35.715542,51.300765],[35.715741,51.300392],[35.716436,51.299864],[35.732645,51.288868],[35.733302,51.28842],[35.733736,51.287996],[35.733986,51.287506],[35.736317,51.279338],[35.736909,51.277548],[35.737073,51.276068],[35.737344,51.272777],[35.741351,51.267366],[35.746602,51.261754],[35.75061,51.255142],[35.753449,51.25022],[35.754089,51.249316],[35.754414,51.24838],[35.753846,51.245206],[35.753161,51.242363],[35.752785,51.239904],[35.751929,51.235663],[35.751414,51.233958],[35.751137,51.232182],[35.7512,51.231364],[35.752413,51.228484],[35.753883,51.225636],[35.754074,51.224526],[35.753923,51.223348],[35.753847,51.221052],[35.753565,51.220686],[35.754592,51.216055],[35.754674,51.215611],[35.755281,51.210969],[35.7564,51.206086],[35.756449,51.205291],[35.766625,51.200267],[35.767579,51.200148]],\"type\":\"LineString\"},\"ref\":\"38Н-091\",\"weight\":1861.2,\"mode\":\"driving\"},{\"intersections\":[{\"out\":2,\"in\":3,\"entry\":[true,true,true,false],\"location\":[35.767579,51.200148],\"bearings\":[30,90,180,285]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.762403,51.185035],\"bearings\":[30,150,210]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.754649,51.175783],\"bearings\":[15,60,195,240]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.749248,51.166094],\"bearings\":[0,180,240]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.749303,51.165782],\"bearings\":[0,105,180]},{\"out\":1,\"in\":2,\"entry\":[true,true,false],\"location\":[35.750411,51.162647],\"bearings\":[60,195,345]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.748074,51.16162],\"bearings\":[60,240,330]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.74425,51.159476],\"bearings\":[0,180,240]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.74425,51.159092],\"bearings\":[0,90,195]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.744092,51.158395],\"bearings\":[15,150,210,315]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":178,\"location\":[35.767579,51.200148],\"type\":\"turn\",\"bearing_before\":101,\"modifier\":\"right\"},\"duration\":480.2,\"distance\":5336.2,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.767579,51.200148],[35.767645,51.189893],[35.762403,51.185035],[35.757992,51.180643],[35.756239,51.178249],[35.754649,51.175783],[35.751817,51.171274],[35.749964,51.168355],[35.749605,51.167732],[35.749094,51.166847],[35.749248,51.166094],[35.749303,51.165782],[35.749361,51.165445],[35.749482,51.164689],[35.750411,51.162647],[35.750232,51.162284],[35.748074,51.16162],[35.744719,51.16059],[35.744576,51.160487],[35.74425,51.160254],[35.74425,51.159476],[35.74425,51.159092],[35.744092,51.158395],[35.743795,51.157965],[35.743353,51.15758]],\"type\":\"LineString\"},\"ref\":\"38Н-010\",\"weight\":480.2,\"mode\":\"driving\"},{\"intersections\":[{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.743353,51.15758],\"bearings\":[30,75,240]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.739493,51.154784],\"bearings\":[45,150,240]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":246,\"location\":[35.743353,51.15758],\"type\":\"turn\",\"bearing_before\":215,\"modifier\":\"straight\"},\"duration\":77.5,\"distance\":1075.2,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.743353,51.15758],[35.74301,51.15749],[35.742653,51.157356],[35.742359,51.15718],[35.741021,51.156007],[35.740456,51.155438],[35.739952,51.155025],[35.739493,51.154784],[35.738031,51.15427],[35.737084,51.154053],[35.735677,51.153829],[35.730486,51.153202]],\"type\":\"LineString\"},\"ref\":\"38К-028\",\"weight\":77.5,\"mode\":\"driving\"},{\"intersections\":[{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.730486,51.153202],\"bearings\":[75,120,135,300]},{\"out\":0,\"in\":2,\"entry\":[true,true,false],\"location\":[35.734044,51.130283],\"bearings\":[165,270,345]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.739991,51.104994],\"bearings\":[15,105,195]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.737613,51.098244],\"bearings\":[15,75,195]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.729677,51.090218],\"bearings\":[30,210,315]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.728301,51.089128],\"bearings\":[45,180,285]},{\"out\":1,\"in\":3,\"entry\":[true,true,true,false],\"location\":[35.733073,51.082451],\"bearings\":[75,150,285,345]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.73472,51.077565],\"bearings\":[0,105,180,270]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.734726,51.077137],\"bearings\":[0,75,180,270]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.734364,51.075778],\"bearings\":[30,105,210]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.733888,51.075392],\"bearings\":[45,75,225]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":142,\"location\":[35.730486,51.153202],\"type\":\"turn\",\"bearing_before\":258,\"modifier\":\"left\"},\"duration\":627.9,\"distance\":9499.6,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.730486,51.153202],[35.731164,51.152655],[35.731589,51.152221],[35.731757,51.151901],[35.731829,51.15149],[35.73182,51.150788],[35.729883,51.144069],[35.731895,51.137185],[35.734044,51.130283],[35.735971,51.123897],[35.736071,51.11787],[35.736649,51.117038],[35.738838,51.11478],[35.741805,51.111647],[35.741905,51.11148],[35.741963,51.111286],[35.741853,51.110535],[35.739991,51.104994],[35.737613,51.098244],[35.737212,51.097107],[35.731861,51.093558],[35.73048,51.091068],[35.729677,51.090218],[35.729276,51.089794],[35.728301,51.089128],[35.728301,51.088883],[35.728629,51.088266],[35.729141,51.087893],[35.730022,51.087352],[35.73123,51.085808],[35.732331,51.083761],[35.732637,51.083179],[35.733073,51.082451],[35.734506,51.080362],[35.734713,51.079978],[35.734791,51.079607],[35.734805,51.079231],[35.734755,51.078289],[35.73472,51.077565],[35.734726,51.077137],[35.734736,51.07639],[35.734671,51.076042],[35.734364,51.075778],[35.734215,51.075642],[35.734064,51.075521],[35.733888,51.075392],[35.733638,51.075227],[35.733381,51.075058],[35.732957,51.074796],[35.732159,51.074331],[35.730989,51.073655]],\"type\":\"LineString\"},\"ref\":\"38Н-010\",\"weight\":627.9,\"mode\":\"driving\"},{\"intersections\":[{\"out\":1,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.730989,51.073655],\"bearings\":[45,135,225,300]},{\"out\":1,\"in\":3,\"entry\":[true,true,true,false],\"location\":[35.762367,51.049755],\"bearings\":[60,120,255,315]},{\"out\":0,\"in\":2,\"entry\":[true,true,false],\"location\":[35.819229,51.023995],\"bearings\":[90,240,285]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.841521,51.022052],\"bearings\":[0,45,225]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.840816,51.021676],\"bearings\":[60,150,225]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.834964,51.01593],\"bearings\":[30,210,300]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.833453,51.014476],\"bearings\":[30,210,270]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.833314,51.014342],\"bearings\":[30,120,210]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.826915,51.007221],\"bearings\":[45,225,285]},{\"out\":2,\"in\":0,\"entry\":[false,true,true],\"location\":[35.826635,51.007015],\"bearings\":[45,165,225]},{\"out\":2,\"in\":0,\"entry\":[false,true,true,true],\"location\":[35.824522,51.005167],\"bearings\":[30,120,210,285]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.822829,51.003613],\"bearings\":[30,210,240]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.821095,51.002019],\"bearings\":[30,210,315]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.82037,51.000844],\"bearings\":[15,195,285]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.819719,50.999729],\"bearings\":[15,195,300]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.817233,50.996314],\"bearings\":[30,210,300]},{\"out\":1,\"in\":0,\"entry\":[false,true,true],\"location\":[35.815917,50.994314],\"bearings\":[15,195,300]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":133,\"location\":[35.730989,51.073655],\"type\":\"turn\",\"bearing_before\":226,\"modifier\":\"left\"},\"duration\":1242.9,\"distance\":13805,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.730989,51.073655],[35.733086,51.072378],[35.735006,51.071067],[35.736554,51.069884],[35.741564,51.066],[35.746313,51.062273],[35.748001,51.060965],[35.749845,51.059446],[35.752303,51.057437],[35.756805,51.053757],[35.758812,51.052261],[35.761236,51.050486],[35.762367,51.049755],[35.780732,51.040976],[35.788481,51.037207],[35.79541,51.0338],[35.802778,51.030227],[35.806528,51.028395],[35.808405,51.027456],[35.809309,51.02704],[35.810222,51.026667],[35.812642,51.02577],[35.81511,51.024951],[35.818037,51.02424],[35.81907,51.024015],[35.819229,51.023995],[35.819372,51.023985],[35.819534,51.02399],[35.819808,51.024022],[35.82103,51.024217],[35.822599,51.024474],[35.823656,51.024641],[35.824575,51.024789],[35.82526,51.024889],[35.825945,51.024965],[35.826404,51.025008],[35.827341,51.025094],[35.828257,51.025178],[35.828893,51.025238],[35.829498,51.025293],[35.829797,51.025314],[35.830124,51.025338],[35.830418,51.025338],[35.830683,51.025317],[35.83099,51.025276],[35.831444,51.025206],[35.831968,51.025126],[35.832431,51.025054],[35.832928,51.02498],[35.833554,51.024925],[35.83384,51.024904],[35.834169,51.024898],[35.834471,51.024894],[35.835291,51.024875],[35.835824,51.024787],[35.836233,51.024657],[35.838857,51.023694],[35.839624,51.023376],[35.840495,51.022878],[35.841501,51.022182],[35.841521,51.022052],[35.841316,51.021911],[35.840816,51.021676],[35.840239,51.021382],[35.839645,51.021021],[35.839062,51.020506],[35.838466,51.019844],[35.837476,51.018725],[35.836293,51.017327],[35.834964,51.01593],[35.833868,51.014874],[35.833453,51.014476],[35.833314,51.014342],[35.833115,51.014151],[35.832558,51.012983],[35.83209,51.012445],[35.831626,51.011961],[35.830415,51.010691],[35.829299,51.009531],[35.82822,51.008424],[35.827099,51.007362],[35.826915,51.007221],[35.826704,51.007059],[35.826635,51.007015],[35.82627,51.006778],[35.824522,51.005167],[35.823299,51.00404],[35.822829,51.003613],[35.821643,51.002534],[35.821095,51.002019],[35.820845,51.001681],[35.820611,51.001246],[35.82037,51.000844],[35.819719,50.999729],[35.819622,50.999563],[35.817233,50.996314],[35.816704,50.995594],[35.816358,50.995139],[35.816117,50.99477],[35.815975,50.994477],[35.815917,50.994314],[35.815859,50.994108],[35.815805,50.993795],[35.815814,50.993416],[35.815803,50.99317],[35.815771,50.992976]],\"type\":\"LineString\"},\"ref\":\"38Н-428\",\"weight\":1242.9,\"mode\":\"driving\"},{\"intersections\":[{\"in\":0,\"entry\":[true],\"location\":[35.815771,50.992976],\"bearings\":[6]}],\"driving_side\":\"right\",\"maneuver\":{\"bearing_after\":0,\"bearing_before\":186,\"type\":\"arrive\",\"location\":[35.815771,50.992976]},\"duration\":0,\"distance\":0,\"name\":\"\",\"geometry\":{\"coordinates\":[[35.815771,50.992976],[35.815771,50.992976]],\"type\":\"LineString\"},\"ref\":\"38Н-428\",\"weight\":0,\"mode\":\"driving\"}],\"weight\":5923.1,\"distance\":56085.8,\"summary\":\"38Н-010, 38Н-428\",\"duration\":5923.1}],\"weight_name\":\"routability\",\"geometry\":{\"coordinates\":[[35.715532,51.403895],[35.714395,51.403632],[35.713927,51.40359],[35.713432,51.40364],[35.713379,51.40372],[35.712823,51.403859],[35.712184,51.403811],[35.71134,51.404018],[35.710659,51.403944],[35.709671,51.403519],[35.709299,51.403369],[35.708632,51.402767],[35.707747,51.402515],[35.706016,51.401587],[35.704708,51.400842],[35.703306,51.400114],[35.703112,51.399823],[35.702893,51.399688],[35.702574,51.399632],[35.701953,51.39973],[35.702127,51.399213],[35.702746,51.398279],[35.703945,51.396716],[35.705049,51.395602],[35.706468,51.394095],[35.707641,51.392725],[35.708825,51.391309],[35.71146,51.388153],[35.712003,51.387851],[35.718622,51.384908],[35.725169,51.382053],[35.725698,51.381593],[35.726882,51.376671],[35.727019,51.376104],[35.727451,51.373443],[35.725181,51.366182],[35.722177,51.355301],[35.722865,51.350178],[35.723691,51.344966],[35.724942,51.340328],[35.726616,51.335928],[35.727679,51.333301],[35.728245,51.331835],[35.728579,51.331228],[35.729015,51.330872],[35.729952,51.330263],[35.730761,51.329656],[35.731879,51.32876],[35.731891,51.328604],[35.731751,51.327898],[35.731502,51.327168],[35.731162,51.326671],[35.730797,51.326215],[35.730171,51.325729],[35.728895,51.324818],[35.727862,51.324172],[35.726622,51.323621],[35.725977,51.323334],[35.72582,51.323264],[35.725109,51.322877],[35.724355,51.322262],[35.723395,51.321453],[35.71937,51.322537],[35.718089,51.322934],[35.716951,51.323452],[35.715859,51.310799],[35.715145,51.302876],[35.715145,51.301696],[35.715393,51.301106],[35.715542,51.300765],[35.715741,51.300392],[35.716436,51.299864],[35.732645,51.288868],[35.733302,51.28842],[35.733736,51.287996],[35.733986,51.287506],[35.736317,51.279338],[35.736909,51.277548],[35.737073,51.276068],[35.737344,51.272777],[35.741351,51.267366],[35.746602,51.261754],[35.75061,51.255142],[35.753449,51.25022],[35.754089,51.249316],[35.754414,51.24838],[35.753846,51.245206],[35.753161,51.242363],[35.752785,51.239904],[35.751929,51.235663],[35.751414,51.233958],[35.751137,51.232182],[35.7512,51.231364],[35.752413,51.228484],[35.753883,51.225636],[35.754074,51.224526],[35.753923,51.223348],[35.753847,51.221052],[35.753565,51.220686],[35.754592,51.216055],[35.754674,51.215611],[35.755281,51.210969],[35.7564,51.206086],[35.756449,51.205291],[35.766625,51.200267],[35.767579,51.200148],[35.767645,51.189893],[35.762403,51.185035],[35.757992,51.180643],[35.756239,51.178249],[35.754649,51.175783],[35.751817,51.171274],[35.749964,51.168355],[35.749605,51.167732],[35.749094,51.166847],[35.749248,51.166094],[35.749303,51.165782],[35.749361,51.165445],[35.749482,51.164689],[35.750411,51.162647],[35.750232,51.162284],[35.748074,51.16162],[35.744719,51.16059],[35.744576,51.160487],[35.74425,51.160254],[35.74425,51.159476],[35.74425,51.159092],[35.744092,51.158395],[35.743795,51.157965],[35.743353,51.15758],[35.74301,51.15749],[35.742653,51.157356],[35.742359,51.15718],[35.741021,51.156007],[35.740456,51.155438],[35.739952,51.155025],[35.739493,51.154784],[35.738031,51.15427],[35.737084,51.154053],[35.735677,51.153829],[35.730486,51.153202],[35.731164,51.152655],[35.731589,51.152221],[35.731757,51.151901],[35.731829,51.15149],[35.73182,51.150788],[35.729883,51.144069],[35.731895,51.137185],[35.734044,51.130283],[35.735971,51.123897],[35.736071,51.11787],[35.736649,51.117038],[35.738838,51.11478],[35.741805,51.111647],[35.741905,51.11148],[35.741963,51.111286],[35.741853,51.110535],[35.739991,51.104994],[35.737613,51.098244],[35.737212,51.097107],[35.731861,51.093558],[35.73048,51.091068],[35.729677,51.090218],[35.729276,51.089794],[35.728301,51.089128],[35.728301,51.088883],[35.728629,51.088266],[35.729141,51.087893],[35.730022,51.087352],[35.73123,51.085808],[35.732331,51.083761],[35.732637,51.083179],[35.733073,51.082451],[35.734506,51.080362],[35.734713,51.079978],[35.734791,51.079607],[35.734805,51.079231],[35.734755,51.078289],[35.73472,51.077565],[35.734726,51.077137],[35.734736,51.07639],[35.734671,51.076042],[35.734364,51.075778],[35.734215,51.075642],[35.734064,51.075521],[35.733888,51.075392],[35.733638,51.075227],[35.733381,51.075058],[35.732957,51.074796],[35.732159,51.074331],[35.730989,51.073655],[35.733086,51.072378],[35.735006,51.071067],[35.736554,51.069884],[35.741564,51.066],[35.746313,51.062273],[35.748001,51.060965],[35.749845,51.059446],[35.752303,51.057437],[35.756805,51.053757],[35.758812,51.052261],[35.761236,51.050486],[35.762367,51.049755],[35.780732,51.040976],[35.788481,51.037207],[35.79541,51.0338],[35.802778,51.030227],[35.806528,51.028395],[35.808405,51.027456],[35.809309,51.02704],[35.810222,51.026667],[35.812642,51.02577],[35.81511,51.024951],[35.818037,51.02424],[35.81907,51.024015],[35.819229,51.023995],[35.819372,51.023985],[35.819534,51.02399],[35.819808,51.024022],[35.82103,51.024217],[35.822599,51.024474],[35.823656,51.024641],[35.824575,51.024789],[35.82526,51.024889],[35.825945,51.024965],[35.826404,51.025008],[35.827341,51.025094],[35.828257,51.025178],[35.828893,51.025238],[35.829498,51.025293],[35.829797,51.025314],[35.830124,51.025338],[35.830418,51.025338],[35.830683,51.025317],[35.83099,51.025276],[35.831444,51.025206],[35.831968,51.025126],[35.832431,51.025054],[35.832928,51.02498],[35.833554,51.024925],[35.83384,51.024904],[35.834169,51.024898],[35.834471,51.024894],[35.835291,51.024875],[35.835824,51.024787],[35.836233,51.024657],[35.838857,51.023694],[35.839624,51.023376],[35.840495,51.022878],[35.841501,51.022182],[35.841521,51.022052],[35.841316,51.021911],[35.840816,51.021676],[35.840239,51.021382],[35.839645,51.021021],[35.839062,51.020506],[35.838466,51.019844],[35.837476,51.018725],[35.836293,51.017327],[35.834964,51.01593],[35.833868,51.014874],[35.833453,51.014476],[35.833314,51.014342],[35.833115,51.014151],[35.832558,51.012983],[35.83209,51.012445],[35.831626,51.011961],[35.830415,51.010691],[35.829299,51.009531],[35.82822,51.008424],[35.827099,51.007362],[35.826915,51.007221],[35.826704,51.007059],[35.826635,51.007015],[35.82627,51.006778],[35.824522,51.005167],[35.823299,51.00404],[35.822829,51.003613],[35.821643,51.002534],[35.821095,51.002019],[35.820845,51.001681],[35.820611,51.001246],[35.82037,51.000844],[35.819719,50.999729],[35.819622,50.999563],[35.817233,50.996314],[35.816704,50.995594],[35.816358,50.995139],[35.816117,50.99477],[35.815975,50.994477],[35.815917,50.994314],[35.815859,50.994108],[35.815805,50.993795],[35.815814,50.993416],[35.815803,50.99317],[35.815771,50.992976]],\"type\":\"LineString\"},\"weight\":5923.1,\"distance\":56085.8,\"duration\":5923.1}]}";
        String string = "{\"code\":\"Ok\",\"waypoints\":[{\"hint\":\"ZBHJgf___39oAAAA5QAAAAAAAAAAAAAA9deLQgwrpkIAAAAAAAAAAGgAAADlAAAAAAAAAAAAAAB2GQEA710QA575IAI3XhAD4vggAgAAjwRJZz7V\",\"distance\":21.852389,\"location\":[51.404271,35.715486],\"name\":\"برادران غفاری\"},{\"hint\":\"hx9IkogfSJJTAAAAZAAAAAAAAAAAAAAAYU1gQuvnhEIAAAAAAAAAAFMAAABkAAAAAAAAAAAAAAB2GQEACxIKA3TCIgK-EQoDVMMiAgAA7w9JZz7V\",\"distance\":25.809088,\"location\":[50.991627,35.832436],\"name\":\"بنفشه ۲\"}],\"routes\":[{\"legs\":[{\"steps\":[],\"weight\":2616.6,\"distance\":47121.8,\"summary\":\"\",\"duration\":2616.6}],\"weight_name\":\"routability\",\"geometry\":{\"coordinates\":[[51.404271,35.715486],[51.404441,35.719877],[51.398405,35.7246],[51.386162,35.723484],[51.368414,35.729055],[51.368969,35.730579],[51.354348,35.715575],[51.342837,35.71218],[51.309228,35.717186],[51.249088,35.714725],[51.206843,35.721106],[51.145533,35.744333],[51.117686,35.741937],[51.091777,35.745255],[51.042921,35.772476],[51.026726,35.785636],[51.00557,35.790968],[51.001375,35.798254],[50.993504,35.79957],[50.986675,35.811789],[50.994636,35.826917],[50.992616,35.826716],[50.991627,35.832436]],\"type\":\"LineString\"},\"weight\":2616.6,\"distance\":47121.8,\"duration\":2616.6}]}";

        String string2 = "{x{bcA}}m`aBmqGsIefHfxJvdAd|Ve{Ifta@g~Aua@vh\\xp[dsElnU{wHps`AxxCvmtBymKhoqAujl@zvvBvtClku@knEhrq@idt@nl~AouXds^glIfih@kfMdeGgqA|jNuzVxiLop\\qpNpKf}BodJx|@";

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                try {
                    function2(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                function(string2);
            }
        }, 1000);

    }

        private void function(String str) {
        List<LatLng> path = PolyUtil.decode(str);
        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED).geodesic(true);
        for (int z = 0; z < path.size(); z++) {
            LatLng point = path.get(z);
            System.out.println("pathhhh");
            options.add(point);
        }
//        line = mMap.addPolyline(options);
        mMap.addPolyline(options);



            int padding = 100; // padding around start and end marker
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (int z = 0; z < path.size(); z++) {
                LatLng point = path.get(z);
                builder.include(path.get(z));
                options.add(point);
            }
//        line = mMap.addPolyline(options);
            mMap.addPolyline(options);

            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
    }

    private void function2(String str) throws JSONException {
        JSONObject coinJson = new JSONObject(str);
        JSONObject jsonObject = (JSONObject) coinJson.getJSONArray("routes").get(0);
        JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
        JSONArray array = jsonObject1.getJSONArray("coordinates");
        ArrayList<LatLng> path = new ArrayList<>();
        for(int i=0; i<array.length(); i++) {
            String [] map = array.getString(i).split(",");
            double lng = Double.parseDouble(map[0].substring(1));
            double lat = Double.parseDouble(map[1].substring(0, map[1].length()-1));
            path.add(new LatLng(lat, lng));
        }

        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED).geodesic(true);

        int padding = 100; // padding around start and end marker
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int z = 0; z < path.size(); z++) {
            LatLng point = path.get(z);
            System.out.println("pathhhh");
            builder.include(path.get(z));
            options.add(point);
        }
//        line = mMap.addPolyline(options);
        mMap.addPolyline(options);

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat.checkSelfPermission
                            (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1);
                //return;
            } else {
//                googleMap.setMyLocationEnabled(true);
//
//                LatLng loc = new LatLng(31.492370, 74.329060);
//
//                googleMap.addMarker(new MarkerOptions().position(loc).title("I am here"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        case 1:
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
            // permission not granted
        }
        else {
            // permission granted
        }
//        break;
        //default:
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //}
}

//}