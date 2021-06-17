
<h1 align="center">BEly</h1>
<p align="center">Bilibili Api 的 kotlin 实现</p>

此项目包括对 [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) 的实现
目前还在施工中

bilibili api 包括 公共api(不需要登录认证) 和需要登录的api([bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect))


```kotlin
import com.elouyi.bely.BiliBotFactory
import com.elouyi.bely.config.BotConfiguration

// 通过 uid 构建一个 BiliBot，如果没有检测到登录缓存信息将会进行登录操作
val bot = BiliBotFactory.newAppBot(123456) 

// 也可以进行配置
val bot = BiliBotFactory.newAppBot(123456) {  // this: BotConfigurationBuilder
    loginMethod(BotConfiguration.LoginMethod.QR_CODE)  // 登录方式，目前仅支持是二维码
    account("账号")  // 账号 用于账号密码登录
    password("密码")  // 密码 用于账号密码登录
}

// bili api
bot.biliApi

bot.biliApi.xxx()
...

```
目前实现的api:
[publicApi](/bely-api/src/main/kotlin/publicapi/PublicApi.kt)
[BiliApi](/bely-api/src/main/kotlin/biliapi/BiliApi.kt)
[WebBiliApi](/bely-api/src/main/kotlin/biliapi/WebBiliApi.kt)