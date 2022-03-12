import com.rsk.Person
import java.io.FileReader
import java.io.IOException
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    displayExample("Hello, World.")
    log(logLevel = 2, message = "test")
    println("Hello, World.".AddOne())

    println(fib(10000, BigInteger("1"), BigInteger("0")))

}


// tail recursive helps avoid stack overflows
tailrec fun fib(n: Int, a: BigInteger, b: BigInteger): BigInteger {
    return if (n == 0) b else fib(n-1, a+b, a)
}


fun displayExample(message:String) : Boolean {
    println(message)
    return true
}


// extension function example
fun String.AddOne() : String {
    var one = "1"
    return this+one
}


// default parameter + override
@JvmOverloads
fun log(message: String, logLevel: Int = 1) {
    println(message + logLevel)
}


// one line functions
fun max(a: Int, b: Int): Int = if (a > b) a else b

fun reading() {
    var reader = FileReader("filename")

    try {
        reader.read()
    } catch(e:IOException) {
        println("ERROR")
    } finally {
        reader.close()
    }
}


fun basics() {
    println("Hello World!")

    // val says immutable (constant) var can also be used
    val oliver = Person(21)
    oliver.Name = "Oliver"


    println("Name is ${oliver.Name}")
    println("Age is ${oliver.Age}")
    oliver.Age = 22
    println("Age is ${oliver.Age}")
    oliver.display()

    oliver.displayWithLambda(::printName)

    var q:Question? = Question()
    q?.Answer = "41"  // question mark says if q isn't null then do it. Does a null check for you
    q?.display()

    println("The answer to the question ${q?.question} is ${q?.Answer}")
    q?.printResult()


    val message:String = if(q?.Answer == q?.CorrectAnswer) {
        "You were correct"
    }
    else {
        "Try again?"
    }

    println(message)


    val number:Int? = try {
        Integer.parseInt(q?.Answer)
    } catch(e:java.lang.NumberFormatException){
        null
    }
    println("number is $number")
}


fun ranges(){
    var numbers = listOf(1,2,3,4,5) // range can be anything that has comparable (such as 'a'..'z')

    for (i in numbers) {
        println(i)
    }

    var ages = TreeMap<String, Int>()
    ages["Kevin"] = 35
    ages["Michael"] = 45
    ages["Dwight"] = 34
    ages["Jim"] = 31

    for((name, age) in ages){
        println("$name is $age")
    }

    for((index, element) in numbers.withIndex()){
        println("$element at $index")
    }
}


fun printName(name: String){
    println(name)
}


class Question() {
    var Answer:String? = "" //Question mark means string can be null
    val CorrectAnswer = "42"
    var question: String = "What is the answer to life?"

    fun display() {
        println("You said $Answer")  // dollar is saying that its a variable
    }

    fun printResult(){
        when (Answer) {
            CorrectAnswer -> println("You were correct")
            else -> println("Try again?")
        }
    }

    // creates a plus function
    infix fun Question.plus(other: Question) : Question {
        var q = Question()
        q.question = this.question + other.question
        return q
    }
}