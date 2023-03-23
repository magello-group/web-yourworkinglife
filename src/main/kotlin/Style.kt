import csstype.url

class Style(val styleType: String) {
    var fontSmall: Int = 20
    var fontMedium: Int = 26
    var fontLarge: Int = 30

    var leftPXOBSText00: Int = 230
    var topPXOBSText00: Int = 6

    var leftPX01: Int = 20

    var topPX01: Int = 20
    var topPX02: Int = 580

    var topPXTable01: Int = 120
    var leftPXTable01: Int = 20

    var topPXTable02: Int = 150
    var leftPXTable02: Int = 20

    var topPXStory01: Int = 110
    var leftPXStory01: Int = 20

    var topPXbutton01: Int = 20
    var leftPXbutton01: Int = 48

    var topPXTitle: Int = 50
    var leftPXTitle: Int = 20

    var widthPXInputName: Int = 200
    var widthPXInputAge: Int = 30
    var widthPXInputPension: Int = 30

    //inti
    var topPXInitCloud: Int = 209
    var topPXInitAnimation: Int = 360
    var topPXInitStreck: Int = 547
    var topPXInitStatus: Int = 580

    //pensioner
    var topPXPensionerAnimation: Int = 780
    var topPXPensionerStreck: Int = 960 // +180
    var topPXPensionerStatus: Int = 970

    //profession
    var topPXProfessionCloud: Int = 365
    var topPXProfessionAnimation: Int = 520 // +151
    var topPXProfessionStreck: Int = 707 // +187
    var topPXProfessionStatus: Int = 735

    //Event
    var topPXEventCloud: Int = 520
    var topPXEventAnimation: Int = 670 // +150
    var topPXEventStreck: Int = 863 // +193
    var topPXEventStatus: Int = 900

    var streck: String = "streck002.jpg"
    var streckStones01: String = "streck003.jpg"
    var streckStones02: String = "streck004.jpg"
    var streckLandscape01: String = "streck005.jpg"
    var streckLandscape02: String = "streck006.jpg"
    var streckStones03: String = "streck007.jpg"
    var streckgreen: String = "streck008.jpg"
    var streckTree01: String = "strecktree0030.png"
    var streckTree02: String = "strecktree0010.png"
    var streckTree03: String = "strecktree0020.png"
    var streckHouse01: String = "streckhouse001.jpg"
    var streckHouse02: String = "streckhouse002.jpg"
    var streckLada: String = "streckhouse003.jpg"
    var streckWater: String= "streckwater001.png"
    var streckSnow: String= "strecksnow001.png"
    var streckMontain: String= "streckmontain001.png"
    var streckSlang01: String = "streckslang0020.png"
    var streckSlang02: String = "streckslang0030.png"
    var streckSkola: String = "streckskola001.jpg"
    var streckTraining: String = "strecktraining0030.png"
    var streckHouse03: String = "streckhouse003.jpg"
    var streckHospital: String = "streckhospital001.jpg"
    var streckFabric: String = "streckfabrik001.jpg"


    var streckSol: String = "sol.png"
    var streckOnlySol: String = "solsol.png"
    var streckRegn: String = "regn.png"
    var streckHeart: String = "heart.png"
    var streckCloudHeart: String = "molnheart.png"
    var streckCloud: String = "moln.png"
    var streckWidth: Int = 13
    var streckOnlySolWidth: Int = 10

    var streckMyraFromLeft: String = "myra001.jpg"
    var streckMyraFromRight: String = "myra002.jpg"
    var streckHumlaFromLeft: String = "bumbelbee001.jpg"
    var streckHumlaFromRight: String = "bumbelbee002.jpg"
    var streckChair: String = "chair001.jpg"
    var streckIcecream: String = "icescream001.jpg"
    var streckBird: String = "bird001.jpg"
    var streckMouse: String = "mouse001.jpg"

    var streckSlalom: String = "streckslalom100.jpg"
    var streckTravel: String = "strecktraveler1300.jpg"
    var streckKapten: String = "streckkapten001.jpg"
    var streckPilot: String = "streckpilot1400.jpg"
    var streckFireman: String = "streckbrandman001.jpg"
    var streckAgent: String = "streckagent1300.jpg"
    var streckPolis: String = "streckpolis1300.jpg"
    var streckProgrammer: String = "streckprogrammer100.jpg"
    var streckBoss: String = "streckboss300.jpg"
    var streckAdvokat: String = "streckadvokat300.jpg"
    var streckDoctor: String = "streckdoctor300.jpg"
    var streckGubbe: String = "streckgubbe1300.jpg"
    var streckSurfer: String = "streckvacation001.jpg"
    var streckMusiker: String = "streckmusiker001.jpg"
    var streckPainter: String = "streckpainter100.jpg"
    var streckGardener: String = "streckgardener001.jpg"
    var streckBonde: String = "strecktraktor400.jpg"
    var streckYoga: String = "streckyoga1300.jpg"
    var streckBuilder: String = "strecksnickare001.jpg"
    var streckPT: String = "streckpt100.jpg"
    var streckArtist01: String = "streckgubbe1050.jpg"
    var streckArtist02: String = "streckgubbe1051.jpg"
    var streckSocial: String = "strecksocial001.jpg"
    var streckWriter: String = "streckwriter400.jpg"
    var streckProfessor: String = "streckprofessor300.jpg"
    var streckRide: String = "streckgubberide1000.jpg"
    var streckClown: String = "streckgubbeclown.jpg"
    var streckActress: String = "streckactress001.jpg"
    var streckGubbe01: String = "streckgubbe1000.jpg"
    var streckGubbe02: String = "streckgubbe1100.jpg"
    var streckAdventure: String = "streckgubbeadventure.jpg"
    var streckGubbeInit01 = "streckgubbe1200.jpg"
    var streckGubbeInit02 = "streckgubbe1300.jpg"
    var streckGubbeInit03 = "streckgubbe1500.jpg"
    var streckGubbeIcecream = "streckgubbeicecream.jpg"
    var streckSparkcyckel01 = "streckgubbe1200.jpg"
    var streckSparkcyckel02 = "streckgubbe1300.jpg"
    var streckSecurity = "strecksecurity300.jpg"
    var streckSolo = "strecksolo300.jpg"
    var streckVD = "streckvd300.jpg"
    var streckAmbassador = "streckambassador300.jpg"



}