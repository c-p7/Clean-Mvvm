# Clean-Mvvm

A Simple Android project which has been implemented using Clean Architecture alongside MVVM design.


### Technologies & Methodologies which used:

- Dagger2
- Coroutines
- Clean Architecture
    -Presentation layer, Domain layer and Data layer 
- MVVM Pattern
- Navigation Graph
- LiveData
- Flow
- Mockk


### The App Scenario

Used REST API, develop a simple application that fetches the
posts from the API and displays them in a list.

[mvvm-domain-module](https://github.com/c-p7/Clean-Mvvm/tree/mvvm-domain-module)
  Domain layer moved to separate module and used into main app using injection

### Used libraries

1. Retrofit - (For fetch data from Network)
2. Glide - (For displaying images using url)

#### Sample Screen

Home Screen (ProductListFragment)
![home-list-view](https://user-images.githubusercontent.com/125017869/226620609-a1edbf32-225b-43a1-ab23-1d702b3dbb73.png)

Product Detail Screen (ProductFragment)
![product-detail-screen](https://user-images.githubusercontent.com/125017869/226621012-ffe1bcc8-aa2d-4676-b7a9-74a9cd206731.png)

