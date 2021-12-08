package data

data class DigitalDisplay(
    var a: Boolean, 
    var b: Boolean, 
    var c: Boolean, 
    var d: Boolean, 
    var e: Boolean, 
    var f: Boolean, 
    var g: Boolean
    ){
    
    fun printNum(){
        if(a) println(" aaaa ")
        if(b && c){
            println("b    c")
            println("b    c")
        }
        else if(b){
            println("b     ")
            println("b     ")
        }
        else if(c){
            println("     c")
            println("     c")
        }
        if(d) println(" dddd ")
        if(e && f){
            println("e    f")
            println("e    f")
        }
        else if(e){
            println("e     ")
            println("e     ")
        }
        else if(f){
            println("     f")
            println("     f")
        }
        if(g) println(" gggg ")
    }
}