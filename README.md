# NewsReader
Practical Test of eBeyonds Digital Marketing
## Goal
Login to app, Populate Top News(Headlines), Search user defined keyword & populate news, Redirect to full article & back to screen

## Installation
Clone this repository and import into **Android Studio**<br />
```https://github.com/chathuraedirisingh/NewsReader.git```

## Configuration
Get a free subscription from [NewsAPI](https://newsapi.org).<br />
Replace the 'KEY' with your apiKey in **build.gradle**
```
debug {
        minifyEnabled false
        buildConfigField("String", "NEWS_API_KEY", '"KEY"')
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
```
## Libraries used:
- [x] EasyPrefs    [link](https://github.com/Pixplicity/EasyPrefs)
- [x] Retrofit    [link](https://github.com/square/retrofit)
- [x] Picasso    [link](https://github.com/square/picasso)
- [x] androidx.browser
- [x] androidx.lifecycle
- [ ] ButterKnife    [link](https://github.com/JakeWharton/butterknife)
- [ ] Dagger 2    [link](https://github.com/google/dagger)
- [ ] Glide    [link](https://github.com/bumptech/glide)


Completed Tasks:
- [x] Generating Classes
- [x] Login & Navigation
- [x] Read Headlines & Search User Preffered Keyword
- [x] Sign Out (Clearing Shared Preference)
- [ ] Bind View (ButterKnife)
- [ ] Dependancy Injection (Dagger 2)