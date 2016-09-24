// This is a generated file, do not edit!
// Generated by R.swift, see https://github.com/mac-cain13/R.swift

import Foundation
import Rswift
import UIKit

/// This `R` struct is code generated, and contains references to static resources.
struct R {
  /// This `R.color` struct is generated, and contains static references to 0 color palettes.
  struct color {
    fileprivate init() {}
  }
  
  /// This `R.file` struct is generated, and contains static references to 2 files.
  struct file {
    /// Resource file `FontAwesome.otf`.
    static let fontAwesomeOtf = FileResource(bundle: _R.hostingBundle, name: "FontAwesome", pathExtension: "otf")
    /// Resource file `GoogleService-Info.plist`.
    static let googleServiceInfoPlist = FileResource(bundle: _R.hostingBundle, name: "GoogleService-Info", pathExtension: "plist")
    
    /// `bundle.url(forResource: "FontAwesome", withExtension: "otf")`
    static func fontAwesomeOtf(_: Void = ()) -> URL? {
      let fileResource = R.file.fontAwesomeOtf
      return fileResource.bundle.url(forResource: fileResource)
    }
    
    /// `bundle.url(forResource: "GoogleService-Info", withExtension: "plist")`
    static func googleServiceInfoPlist(_: Void = ()) -> URL? {
      let fileResource = R.file.googleServiceInfoPlist
      return fileResource.bundle.url(forResource: fileResource)
    }
    
    fileprivate init() {}
  }
  
  /// This `R.font` struct is generated, and contains static references to 1 fonts.
  struct font {
    /// Font `FontAwesome`.
    static let fontAwesome = FontResource(fontName: "FontAwesome")
    
    /// `UIFont(name: "FontAwesome", size: ...)`
    static func fontAwesome(size: CGFloat) -> UIFont? {
      return UIFont(resource: fontAwesome, size: size)
    }
    
    fileprivate init() {}
  }
  
  /// This `R.image` struct is generated, and contains static references to 0 images.
  struct image {
    fileprivate init() {}
  }
  
  /// This `R.nib` struct is generated, and contains static references to 1 nibs.
  struct nib {
    /// Nib `DepartmentDetailView`.
    static let departmentDetailView = _R.nib._DepartmentDetailView()
    
    /// `UINib(name: "DepartmentDetailView", in: bundle)`
    static func departmentDetailView(_: Void = ()) -> UINib {
      return UINib(resource: R.nib.departmentDetailView)
    }
    
    fileprivate init() {}
  }
  
  /// This `R.reuseIdentifier` struct is generated, and contains static references to 2 reuse identifiers.
  struct reuseIdentifier {
    /// Reuse identifier `departmentCell`.
    static let departmentCell: ReuseIdentifier<UITableViewCell> = ReuseIdentifier(identifier: "departmentCell")
    /// Reuse identifier `serviceCell`.
    static let serviceCell: ReuseIdentifier<UITableViewCell> = ReuseIdentifier(identifier: "serviceCell")
    
    fileprivate init() {}
  }
  
  /// This `R.segue` struct is generated, and contains static references to 1 view controllers.
  struct segue {
    /// This struct is generated for `ViewController`, and contains static references to 1 segues.
    struct viewController {
      /// Segue identifier `showService`.
      static let showService: StoryboardSegueIdentifier<UIStoryboardSegue, ViewController, ServiceViewController> = StoryboardSegueIdentifier(identifier: "showService")
      
      /// Optionally returns a typed version of segue `showService`.
      /// Returns nil if either the segue identifier, the source, destination, or segue types don't match.
      /// For use inside `prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?)`.
      static func showService(segue: UIStoryboardSegue) -> TypedStoryboardSegueInfo<UIStoryboardSegue, ViewController, ServiceViewController>? {
        return TypedStoryboardSegueInfo(segueIdentifier: R.segue.viewController.showService, segue: segue)
      }
      
      fileprivate init() {}
    }
    
    fileprivate init() {}
  }
  
  /// This `R.storyboard` struct is generated, and contains static references to 2 storyboards.
  struct storyboard {
    /// Storyboard `LaunchScreen`.
    static let launchScreen = _R.storyboard.launchScreen()
    /// Storyboard `Main`.
    static let main = _R.storyboard.main()
    
    /// `UIStoryboard(name: "LaunchScreen", bundle: ...)`
    static func launchScreen(_: Void = ()) -> UIStoryboard {
      return UIStoryboard(resource: R.storyboard.launchScreen)
    }
    
    /// `UIStoryboard(name: "Main", bundle: ...)`
    static func main(_: Void = ()) -> UIStoryboard {
      return UIStoryboard(resource: R.storyboard.main)
    }
    
    fileprivate init() {}
  }
  
  /// This `R.string` struct is generated, and contains static references to 0 localization tables.
  struct string {
    fileprivate init() {}
  }
  
  fileprivate init() {}
}

struct _R {
  static let applicationLocale = hostingBundle.preferredLocalizations.first.flatMap(Locale.init) ?? Locale.current
  static let hostingBundle = Bundle(identifier: "com.aleksandergrzyb.Queue") ?? Bundle.main
  
  struct nib {
    struct _DepartmentDetailView: NibResourceType {
      let bundle = _R.hostingBundle
      let name = "DepartmentDetailView"
      
      func firstView(owner ownerOrNil: AnyObject?, options optionsOrNil: [NSObject : AnyObject]? = nil) -> DepartmentDetailView? {
        return instantiate(withOwner: ownerOrNil, options: optionsOrNil)[0] as? DepartmentDetailView
      }
      
      fileprivate init() {}
    }
    
    fileprivate init() {}
  }
  
  struct storyboard {
    struct launchScreen: StoryboardResourceWithInitialControllerType {
      typealias InitialController = UINavigationController
      
      let bundle = _R.hostingBundle
      let name = "LaunchScreen"
      
      fileprivate init() {}
    }
    
    struct main: StoryboardResourceWithInitialControllerType {
      typealias InitialController = UINavigationController
      
      let bundle = _R.hostingBundle
      let name = "Main"
      
      fileprivate init() {}
    }
    
    fileprivate init() {}
  }
  
  fileprivate init() {}
}