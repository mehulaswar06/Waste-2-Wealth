package app.waste2wealth.com.dashboard

import android.Manifest
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.waste2wealth.com.R
import app.waste2wealth.com.communities.ui.DummyCards
import app.waste2wealth.com.firebase.firestore.ProfileInfo
import app.waste2wealth.com.firebase.firestore.updateCommunitiesToFirebase
import app.waste2wealth.com.firebase.firestore.updateTagsToFirebase
import app.waste2wealth.com.location.LocationViewModel
import app.waste2wealth.com.navigation.Screens
import app.waste2wealth.com.profile.ProfileImage
import app.waste2wealth.com.rewards.Level
import app.waste2wealth.com.rewards.LevelDialogBox
import app.waste2wealth.com.rewards.levels
import app.waste2wealth.com.tags.wasteGroups
import app.waste2wealth.com.ui.theme.CardColor
import app.waste2wealth.com.ui.theme.CardTextColor
import app.waste2wealth.com.ui.theme.appBackground
import app.waste2wealth.com.ui.theme.monteBold
import app.waste2wealth.com.ui.theme.monteNormal
import app.waste2wealth.com.ui.theme.monteSB
import app.waste2wealth.com.ui.theme.textColor
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import kotlinx.coroutines.delay
import kotlin.system.exitProcess


@Composable
fun NewDashboard(
    navController: NavHostController,
    viewModel: LocationViewModel,
    email: String,
    name: String,
    pfp: String
) {
    var profileList by remember {
        mutableStateOf<List<ProfileInfo>?>(null)
    }
    var address by remember {
        mutableStateOf("")
    }
    var animStart by remember {
        mutableStateOf(false)
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var organization by remember {
        mutableStateOf("")
    }
    var pointsEarned by remember {
        mutableStateOf(0)
    }
    var pointsRedeemed by remember {
        mutableStateOf(0)
    }
    var isCOinVisible by remember {
        mutableStateOf(false)
    }
    var communities by remember { mutableStateOf<List<DummyCards>?>(null) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (animStart) 0f else viewModel.currentProgress,
        label = "",
        animationSpec = tween(500)
    )
    LaunchedEffect(key1 = viewModel.showLevelDialog) {
        if (viewModel.showLevelDialog) {
            animStart = true
            delay(1000)
            animStart = false
        }
    }
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finishAndRemoveTask()
        exitProcess(0)
    }
    JetFirestore(path = {
        collection("Communities")
    }, onRealtimeCollectionFetch = { values, _ ->
        communities = values?.getListOfObjects()
    }) {
        JetFirestore(path = {
            collection("ProfileInfo")
        }, onRealtimeCollectionFetch = { value, _ ->
            profileList = value?.getListOfObjects()
        }) {
            if (profileList != null) {
                for (i in profileList!!) {
                    if (i.email == email) {
                        address = i.address ?: ""
                        gender = i.gender ?: ""
                        phoneNumber = i.phoneNumber ?: ""
                        organization = i.organization ?: ""
                        pointsEarned = i.pointsEarned
                        pointsRedeemed = i.pointsRedeemed
                    }
                }
            }
            LaunchedEffect(key1 = pointsEarned) {
                animStart = true
                if (pointsEarned != 0) {
                    viewModel.pointsEarned = pointsEarned
                    viewModel.getCurrentLevel(pointsEarned, levels)
                    viewModel.currentLevel.value?.let {
                        viewModel.currentProgress = viewModel.getCurrentLevelProgress(
                            pointsEarned,
                            levels
                        )
                        viewModel.remainingPoints = viewModel.pointsRemainingForNextLevel(
                            pointsEarned,
                            levels
                        )
                        delay(1000)
                        animStart = false
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(appBackground)
                        .verticalScroll(rememberScrollState())
                        .then(
                            if (viewModel.showLevelDialog) Modifier.blur(10.dp) else Modifier
                        )
                ) {
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                            .fillMaxWidth(),
                        backgroundColor = CardColor,

                        ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 45.dp,
                                        bottom = 15.dp,
                                        end = 25.dp,
                                        start = 25.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Welcome Back",
                                        color = Color.Gray,
                                        fontSize = 13.sp,
                                        fontFamily = monteSB,
                                        modifier = Modifier.padding(bottom = 7.dp)
                                    )
                                    Text(
                                        text = name,
                                        color = CardTextColor,
                                        fontSize = 20.sp,
                                        fontFamily = monteBold,
                                        modifier = Modifier.padding(bottom = 7.dp)
                                    )
                                    Text(
                                        text = "Start making a difference today!",
                                        color = Color.Gray,
                                        fontSize = 13.sp,
                                        fontFamily = monteSB,
                                        modifier = Modifier.padding(bottom = 7.dp)
                                    )
                                }
                                ProfileImage(
                                    imageUrl = pfp,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .border(
                                            width = 1.dp,
                                            color = textColor,
                                            shape = CircleShape
                                        )
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.navigate(Screens.Profile.route)
                                        }
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp, start = 25.dp, end = 25.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(top = 15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Points Earned",
                                        color = CardTextColor,
                                        fontSize = 14.sp,
                                        fontFamily = monteBold,
                                        softWrap = true,
                                        modifier = Modifier.padding(start = 7.dp)
                                    )
                                    Row(modifier = Modifier.padding(end = 0.dp, top = 7.dp)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.coins),
                                            contentDescription = "coins",
                                            modifier = Modifier
                                                .size(20.dp)
                                                .padding(end = 5.dp),
                                            tint = Color.Unspecified
                                        )
                                        Text(
                                            text = pointsEarned.toString(),
                                            color = CardTextColor,
                                            fontSize = 15.sp,
                                            fontFamily = monteNormal,
                                        )
                                    }

                                }
                                Column(
                                    modifier = Modifier
                                        .padding(top = 15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Points Redeemed",
                                        color = CardTextColor,
                                        fontSize = 14.sp,
                                        fontFamily = monteBold,
                                        softWrap = true,
                                        modifier = Modifier.padding(start = 7.dp)
                                    )
                                    Row(modifier = Modifier.padding(end = 0.dp, top = 7.dp)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.coins),
                                            contentDescription = "coins",
                                            modifier = Modifier
                                                .size(20.dp)
                                                .padding(end = 5.dp),
                                            tint = Color.Unspecified
                                        )
                                        Text(
                                            text = pointsRedeemed.toString(),
                                            color = CardTextColor,
                                            fontSize = 15.sp,
                                            fontFamily = monteNormal,
                                        )
                                    }


                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }



                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .clickable {
                                viewModel.showLevelDialog = true
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                text = "Current Progress",
                                color = textColor,
                                fontSize = 20.sp,
                                fontFamily = monteBold,
                                modifier = Modifier.padding(bottom = 7.dp)
                            )
                            Text(
                                text = "${viewModel.remainingPoints} more points to reach next level",
                                color = textColor,
                                fontSize = 9.sp,
                                fontFamily = monteBold,
                                modifier = Modifier.padding(start = 0.dp)
                            )
                        }

                        ArcComposable(
                            modifier = Modifier.padding(end = 25.dp),
                            text = "${(viewModel.currentProgress * 100).toInt()}%",
                            progress = animatedProgress
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(5.dp),
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ProfileImage(
                                    imageUrl = R.drawable.ic_reportwaste,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .border(
                                            width = 1.dp,
                                            color = textColor,
                                            shape = CircleShape
                                        )
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.navigate(Screens.ReportWaste.route)
                                        }
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "Report Waste",
                                    color = textColor,
                                    fontSize = 13.sp,
                                    fontFamily = monteNormal,
                                    softWrap = true
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(5.dp),
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ProfileImage(
                                    imageUrl = R.drawable.ic_collectwaste,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .border(
                                            width = 1.dp,
                                            color = textColor,
                                            shape = CircleShape
                                        )
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.navigate(Screens.CollectWasteLists.route)
                                        }
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "Collect Waste",
                                    color = textColor,
                                    fontSize = 13.sp,
                                    fontFamily = monteNormal,
                                    softWrap = true
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(5.dp),
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ProfileImage(
                                    imageUrl = R.drawable.ic_rewards,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .border(
                                            width = 1.dp,
                                            color = textColor,
                                            shape = CircleShape
                                        )
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.navigate(Screens.Rewards.route)
                                        }
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "Rewards",
                                    color = textColor,
                                    fontSize = 13.sp,
                                    fontFamily = monteNormal,
                                    softWrap = true
                                )
                            }
                        }

                    }


                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Join Communities",
                            color = textColor,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "View All",
                            color = textColor,
                            fontSize = 15.sp,
                            modifier = Modifier.clickable {
                                navController.navigate(Screens.Community.route)
                            }
                        )
                    }

                    LazyRow(contentPadding = PaddingValues(10.dp)) {
                        items(communities?.take(3) ?: emptyList()) { item ->
                            Card(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(200.dp)
                                    .padding(end = 10.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = 10.dp,
                                backgroundColor = CardColor
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    ProfileImage(
                                        imageUrl = item.image,
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .fillMaxHeight()
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = item.name,
                                            color = CardTextColor,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            softWrap = true
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = item.dateOfEstablishment,
                                            color = CardTextColor,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Button(
                                            onClick = {
                                                updateTagsToFirebase(wasteGroups)
                                            },
                                            shape = RoundedCornerShape(10.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = appBackground
                                            )
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Filled.LocationOn,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp),
                                                    tint = textColor
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(
                                                    text = "Register",
                                                    color = textColor,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Normal
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    val lastTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "Waste Wise",
                            color = lastTextColor.copy(0.75f),
                            fontSize = 33.sp,
                            fontFamily = monteSB,
                        )
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            text = "Rewards Rise",
                            color = lastTextColor.copy(0.5f),
                            fontSize = 23.sp,
                            fontFamily = monteSB,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Crafted with ❤️ by The Centennials",
                            color = lastTextColor.copy(0.75f),
                            fontSize = 10.sp,
                            fontFamily = monteSB,
                        )
                    }

                    Spacer(modifier = Modifier.height(130.dp))

                }
                if (viewModel.showLevelDialog) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        viewModel.currentLevel.value?.let { currentlevel ->
                            isCOinVisible = viewModel.isNewLevelUnlocked(
                                currentlevel,
                                viewModel.pointsEarned,
                                levels
                            )
                            LevelDialogBox(
                                level = currentlevel,
                                progress = viewModel.getCurrentLevelProgress(
                                    viewModel.pointsEarned,
                                    levels
                                ),
                                isLevelUp = viewModel.isNewLevelUnlocked(
                                    currentlevel,
                                    viewModel.pointsEarned,
                                    levels
                                ),
                                isVisible = viewModel.showLevelDialog
                            ) {
                                viewModel.showLevelDialog = false
                            }
                        }
                    }
                }
                if (isCOinVisible) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val currenanim by rememberLottieComposition(
                            spec = LottieCompositionSpec.Asset("confetti.json")
                        )
                        LottieAnimation(
                            composition = currenanim,
                            iterations = 1,
                            contentScale = ContentScale.Crop,
                            speed = 1f,
                            modifier = Modifier
                                .fillMaxSize()
                                .size(250.dp)
                        )
                    }
                    LaunchedEffect(key1 = isCOinVisible) {
                        if (isCOinVisible) {
                            delay(4000)
                            navController.popBackStack()
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun ArcComposable(
    modifier: Modifier,
    text: String,
    progress: Float = 0.5f, // Progress value between 0.0 and 1.0
    completedColor: Color = Color(0xFF48c5a3),
    remainingColor: Color = Color(0xFFe4e4e4),
) {
    val sweepAngle = 180f * progress
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        Canvas(modifier = Modifier.size(70.dp)) {
            // Draw the remaining part of the arc
            drawArc(
                color = remainingColor,
                -180f + sweepAngle,
                180f - sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )

            // Draw the completed part of the arc
            drawArc(
                color = completedColor,
                -180f,
                sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = text,
            color = textColor,
            fontSize = 20.sp
        )
    }
}
