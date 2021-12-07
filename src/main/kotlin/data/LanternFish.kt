package data

data class LanternFish(var timer: Int){
    fun live(): Boolean {
        var birthday = timer == 0
        if(timer == 0) timer = 6
        else timer--
        return birthday;
    }
}