package pl.edu.uwr.pum.lista3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import pl.edu.uwr.pum.lista3.ui.theme.Gray1
import pl.edu.uwr.pum.lista3.ui.theme.Green1
import pl.edu.uwr.pum.lista3.ui.theme.Lista3Theme

class MainActivity : ComponentActivity() {

    val questionBank = arrayListOf(
        Question("Droga mleczna to galaktyka:",
            arrayOf("Spiralna z poprzeczką", "Eliptyczna", "Soczewkowata", "Spiralna bez poprzeczki"), 0),
        Question("Pas Kuipera znajduje się za orbitą:",
            arrayOf("Marsa", "Neptuna", "Jowisza", "Merkurego"), 1),
        Question("Czym są egzoplanety?",
            arrayOf("To planety składające się głównie z gazów", "Są to niewielkie ciała niebieskie",
                "Planety krążące wokół gwiazdy innej niż Słońce", "To nazwa na duże asteroidy"), 2),
        Question("Jaka gwiazda (nie licząc Słońca) znajduje się najbliżej Ziemi?",
            arrayOf("Syriusz", "Proxima Centauri", "Alfa Centauri A", "Alfa Centauri B"), 1),
        Question("Co znajduje się w centrum Drogi Mlecznej",
            arrayOf("Ziemia", "Gromada gwiazd", "Układ Słoneczny", "Supermasywna czarna dziura"), 3),
        Question("Gwiazdy powstają:",
            arrayOf("Podczas zapadania się obłoków molekularnych", "Podczas eksplozji supernowych typu Ia",
                "Dzięki wiatrom kosmicznym", "Żadna odpowiedź nie jest poprawna"), 0),
        Question("Którą z planet NIE zaliczymy do planet wewnętrznych?",
            arrayOf("Ziemia", "Marsa", "Uran", "Wenus"), 2),
        Question("Kratery obserwowalne m.in. na Marsie są wynikiem:",
            arrayOf("Działalności wulkanicznej", "Wielkiego Bombardowania", "Ingerencji człowieka", "Wszystkie odpowiedzi są prawidłowe"), 1),
        Question("Jednostka astronomiczna [1 au] to jednostka",
            arrayOf("Czasu w astronomii", "Miary strumienia świetlnego w układzie SI", "Światłości źródła światła",
                "Odległości - średnia odległość między Ziemią a Słońcem"), 3),
        Question("Czym jest kwazar?",
            arrayOf("Gwiazdą o bardzo dużej masie", "Obiektem gwiazdopodobnym emitującym fale radiowe",
                "Małą galaktyką w grupie galaktyk", "Jednym z etapów życia gwiazdy na ciągu głównym"), 1)
    )

    private var userScore: Int by mutableStateOf(0)
    private var currentQuestionIndex: Int by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuestionScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    fun QuestionScreen() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            var selectedAnswer by rememberSaveable { mutableStateOf(-1) }
            DisposableEffect(currentQuestionIndex) {
                onDispose {
                    selectedAnswer = -1
                }
            }

            var visible by remember { mutableStateOf(true) }
            if(currentQuestionIndex==10) { visible = false}
            if(currentQuestionIndex==0) { visible = true}


            if (visible){
                Text(
                    text = "Pytanie ${currentQuestionIndex + 1}/${questionBank.size}",
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 15.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .align(Alignment.CenterHorizontally),
                    progress = (currentQuestionIndex + 1).toFloat() / questionBank.size
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
            else {
                Spacer(modifier = Modifier.height(40.dp))
            }



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(16.dp)
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
            {
                if(visible){
                    Text(
                        text = questionBank[currentQuestionIndex].textResId,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.secondary)
                            .wrapContentSize(Alignment.Center)
                            .padding(8.dp),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }
                else{
                    Text(
                        text = "Gratulacje",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.secondary)
                            .wrapContentSize(Alignment.Center)
                            .padding(8.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(410.dp)
                    .padding(16.dp)
                    .border(width = 2.dp,Green1, shape = RoundedCornerShape(8.dp))
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.SpaceBetween
                )
                {
                    if(visible){
                        val answers = questionBank[currentQuestionIndex].answers
                        Spacer(modifier = Modifier.height(8.dp))
                        for ((index, answer) in answers.withIndex()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                                    .clickable {
                                        selectedAnswer = index
                                    }
                                    .background(
                                        MaterialTheme.colorScheme.secondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .weight(1f)
                            ) {

                                RadioButton(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically),
                                    selected = index == selectedAnswer,
                                    onClick = {
                                        selectedAnswer = index
                                    }
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 2.dp, end = 5.dp)
                                        .align(Alignment.CenterVertically),
                                    text = answer,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    else {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        {
                            Text(
                                text = "Zdobyłeś ${userScore}/${questionBank.size * 10}",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                                    .padding(8.dp),
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }

            }

            if(visible) {
                Button(
                    onClick = {
                        if (currentQuestionIndex < questionBank.size)
                        {
                            if(selectedAnswer == questionBank[currentQuestionIndex].correctAnswerIndex){
                                userScore += 10
                            }
                            currentQuestionIndex++
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    enabled = selectedAnswer != -1)
                {
                    Text(
                        text = "Następne",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            }
            else {
                Button(
                    onClick = {
                        currentQuestionIndex = 0
                        userScore = 0
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = "Zagraj ponownie",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }

}

