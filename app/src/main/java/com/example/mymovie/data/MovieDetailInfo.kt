package com.example.mymovie.data

class MovieDetailInfo {
    lateinit var title:String
    lateinit var date:String
    var user_rating:Float = 0.0f
    var audience_rating:Float = 0.0f
    var reviewer_rating:Float = 0.0f
    var reservation_rate:Float = 0.0f
    var reservation_grade:Int = 0
    var grade:Int = 0
    lateinit var thumb:String
    lateinit var image:String
    lateinit var photos:String
    lateinit var videos:String
    lateinit var outlinks:String
    lateinit var genre:String
    var duration:Int = 0
    var audience:Int = 0
    lateinit var synopsis:String
    lateinit var director:String
    lateinit var actor:String
    var like:Int = 0
    var dislike:Int = 0
}