# Micro Services Architecture-Spring Cloud Gateway-Eureka Discovery

commands - customers - products Microservices.

## Table of contents

- [Project Architecture](#project-architecture)
- [Eureka Disovery Service](#eureka-disovery-service)
- [command Service](#command-service)
- [Frontend](#frontend)

## Project Architecture

In this project we have the following services:

    1. Customer Service :
        My job is not complicated ,I will handle CRUD operations for our customers ✔

    2. Product service :
        I will be here for handling CRUD operations for invoices ✔

    2. Command service :
        I will be here for handling CRUD operations for orders ✔

    3. OpenFeign :
        I will get customer and product details if any order ask me about them 🤷‍♂️

    4. Consul Discovery Service :
        I will holds the information about all other services 😎

    5. Gateway :
        Your clients will send me their requests, then I will send it to the wanted service with the help of Discovery service 😜

## Eureka Discovery Service

<br>
First, let's run our services :

<br>
<img src="./demo/services.png" width="1000px">
<br>

<br>
then let's check them in the consul interface :

<br>
<img src="./demo/eureka.png" width="1000px">
<br>

## command Service

<br>
<img src="./demo/commands.png" width="1000px">
<br>

<br>
<img src="./demo/1.png" width="1000px">
<br>

## Frontend

<br>
<img src="./demo/2.png" width="1000px">
<br>

<br>
<img src="./demo/3.png" width="1000px">
<br>

<br>
