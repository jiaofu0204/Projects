var express     = require("express"),
    app         = express(),
    bodyParser  = require("body-parser"),
    mongoose    = require("mongoose"),
	flash		= require("connect-flash"),
	passport	= require("passport"),
	LocalStrategy = require("passport-local"),
	methodOverride = require("method-override"),
	Campground  = require("./models/campground"),
	Comment		= require("./models/comment"),
	User		= require("./models/user"),
	Review = require("./models/review"),
	seedDB		= require("./seeds");

//requiring routes
var commentRoutes    = require("./routes/comments"),
	reviewRoutes     = require("./routes/review"),
	campgroundRoutes = require("./routes/campgrounds"),
	indexRoutes		 = require("./routes/index");
  
mongoose.set('useUnifiedTopology', true);
mongoose.set('useFindAndModify', false);
mongoose.connect('mongodb+srv://LeoZhang:Zhang1968@cluster0-2y3fb.mongodb.net/test?retryWrites=true&w=majority', {
	useNewUrlParser: true,
	useCreateIndex: true
}).then(() => {
	console.log('Connected to DB!');
}).catch(err => {
	console.log('ERROR:', err.message);
});
//mongoose.connect('mongodb://localhost/yelp_camp',{useNewUrlParser: true});
app.use(bodyParser.urlencoded({extended: true}));
app.set("view engine", "ejs");
app.use(express.static(__dirname + "/public"));
app.use(methodOverride("_method"));
app.use(flash());
app.locals.moment = require('moment');
//seedDB(); 

// PASSPORT CONFIGURATION
app.use(require("express-session")({
	secret: "Once aging Messi is the best!",
	resave: false,
	saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

app.use(function(req, res, next){
	res.locals.currentUser = req.user;
	res.locals.error = req.flash("error");
	res.locals.success = req.flash("success");
	next();
});

app.use(indexRoutes);
app.use(campgroundRoutes);
app.use(commentRoutes);
app.use("/campgrounds/:id/reviews", reviewRoutes);
		
var port = process.env.PORT || 3000;
app.listen(port,function(){
	console.log("The YelpCamp Server Has Started!");
})