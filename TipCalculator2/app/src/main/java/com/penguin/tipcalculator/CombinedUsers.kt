package com.penguin.tipcalculator

import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

class CombinedUsers {
    // tot number of users
    private var names : HashMap<Int, String> = hashMapOf<Int, String>()
    private var costs : HashMap<Int, Int> = hashMapOf<Int, Int>()

    var total: Int = 0
    var tax : Float? = null
    var tip : Float? = null


    // returns user and
    fun add(id : Int, name : String, cost : Int) {
        this.names.put(id, name)
        this.costs.put(id, cost)
        this.total += cost
    }

    fun delete(id : Int){
        this.names.remove(id)
        this.total -= this.costs[id] as Int
        this.costs.remove(id)
    }

    fun update(id : Int, name : String? = null, cost: Int? = null){
        if (name != null) {
            this.names[id] = name
        }
        if (cost != null){
            total -= this.costs[id] as Int
            total += cost
            this.costs[id] = cost
        }
    }

    fun compute() : HashMap<String, Int>{
        if(tax == null || tip == null){
            throw IllegalArgumentException("tax and tip can not be null")
        }

        var out = hashMapOf<String, Int>()
        var rate = tax as Float + tip as Float

        for((key, value) in this.names){
            out[value] = (costs[key] as Float * rate).roundToInt()
        }
        return out
    }

    fun size() = this.names.size

}