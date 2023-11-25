<div align="center">

## Antdv-Pro-Java

Antdv-Pro的Java后端实现
https://github.com/antdv-pro/antdv-pro

## 介绍

该项目是基于Spring-Boot 3.1、Spring Security、JWT、Oauth2、Hutool集成实现的一套后端项目，可以帮助新手开发者快速对接Antdv-Pro。 数据库依赖于Mysql和Redis

## 使用

### 后端

请先修改本项目内`resources`文件夹下的`application.yaml`文件，修改mysql和redis的登录账户以及密码，

```json
  # 请自行修改mysql、redis户名以及密码
datasource: username: root
password: qwe123
data: redis: database: 1
host: 127.0.0.1
port: 6379
password: qwe123
```

使用Navicat等数据库管理工具，创建一个名为`antd-pro`的数据库。字符集为`utf8mb4`,定序列为`utf8mb4_general_ci`

将项目内的`antd-pro-java.sql`文件导入该数据库中。启动项目即可。

用户表内的用户，用户名与密码一样。即用户名：admin 密码：admin以此类推。

### 前端

Antd-Pro，需要修改项目内的`mist.config.ts`文件，将注释打开

```ts
import { defineConfig } from '@mistjs/cli'

export default defineConfig({
  // 关闭nitro服务
  nitro: false,
})
```

删除项目内的`servers`文件夹，并使用`pnpm install`命令

修改`.env.development`文件

```development
VITE_APP_BASE_API=/api
VITE_APP_BASE_URL=http://127.0.0.1:6688
```

修改`vite.config.ts`文件内的98-102行，将注释打开

```ts
    server: {
      port: 6678,
      host: '0.0.0.0',
      proxy: {
        ...proxyObj,
        [env.VITE_APP_BASE_API]: {
          target: env.VITE_APP_BASE_URL,
          changeOrigin: true,
          rewrite: path => path.replace(new RegExp(`^${env.VITE_APP_BASE_API}`), ''),
        },
      },
    },
```

修改`src-api-common`下的`login.ts`文件内的17-24行

```ts
export function loginApi(params: LoginParams | LoginMobileParams) {
  return usePost<LoginResultModel, LoginParams | LoginMobileParams>('/login', params, {
    // 设置为false的时候不会携带token
    token: false,
    // 是否开启全局请求loading
    loading: true,
  })
}
```

启动项目

```
pnpm dev
```

