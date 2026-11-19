# Navigation elements

## Inventory

| Fragment Class | Title | Layout / XML resource| Top-level? | Superclass|
|:------------------|:---------|:---------------------|:----------:|:---------------------------|
| `SearchFragment` | Search| `fragment_search` |Yes | `Fragment` |
| `SettingsFragment`| Settings | `settings`|Yes| `PreferenceFragmentCompat` |

## Organization

- All fragment classes are either already present in the `edu.cnm.deepdive.skywatch.app.controller` package, or should be created in that package.

- All referenced layouts are either existing layout resources, existing XML resources, or should be created, using a layout resource with root element of `ConstraintLayout` for `Fragment` subclasses, or an XML resource with a `PreferenceScreen` root element for `PreferenceFragmentCompat` subclasses.

## Widget

- Top-level navigation will use a `NavigationView` inside a `DrawerLayout` with a `FragmentContainerView`. (Nav drawer)
  
- Top-level navigation will use a `BottomNavigationView` with a `FragmentContainerView`. (Bottom nav)

- Top-level navigation will use a `NavigationRailView` with a `FragmentContainerView`. (siderail)
- 
- Top-level navigation will use a `PrimaryTabRow` with a `ViewPager2`. (tabbed)

- The menu resource used by the navigation view should be named `main_options`.

## Host

- Navigation controls and controllers should be hosted inside an activity named `MainActivity`, using a layout resource named `activity_main`.

- Activity classes should reside in the `edu.cnm.deepdive.skywatch.app.controller` package.

## Navigation view and fragment synchronization

- Navigation action and display changes will use the Jetpack navigation component, with an `AppBarConfiguration` tied to a `NavController`, and the `NavController` tied to a `NavigationView`. (non-tabbed)

- Navigation action and display changes will use the `ViewPager2` component, driven by a `PrimaryTabRow` and a `FragmentStateAdapter` subclass. (tabbed)

## General

- View binding will be used in all `Fragment` subclasses (other than `PreferenceFragmentCompat` subclasses).

- All view binding instance reference properties should be named `binding`. In Kotlin fragments, this should be a `lateinit var` property; in Kotlin activities, this should be declared as a `val` property, initialized `by lazy`.