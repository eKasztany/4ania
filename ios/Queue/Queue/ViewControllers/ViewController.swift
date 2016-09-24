//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit
import MapKit
import SnapKit

class ViewController: UIViewController {

    enum State {
        case loading
        case loaded([Department])
        case empty
        case error
    }

    enum ViewState {
        case map
        case list
    }

    @IBOutlet weak var toggleButton: UIBarButtonItem!
    @IBOutlet weak var tableView: UITableView!
    var mapView: MKMapView?
    var mapTopConstraint: Constraint? = nil
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(let departments):
                loadAnnotations(annotations: departments)
                tableView.reloadData()
            default:
                break
            }
        }
    }
    var viewState: ViewState = .list {
        didSet {
            switch viewState {
            case .map:
                break
            case .list:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mapView?.delegate = self
        tableView.dataSource = self
        tableView.delegate = self

        FirebaseClient().load(resource: Department.all) { result in
            if let result = result {
                self.state = .loaded(result)
            } else {
                self.state = .error
            }
        }

        RemoteNotificationsManager.register { (result) in
        }

        setNeedsStatusBarAppearanceUpdate()
        createMapView()
        configureBarButtonItem()
    }

    private func createMapView() {
        mapView = MKMapView()
        view.insertSubview(mapView!, aboveSubview: tableView)
        mapView!.snp.makeConstraints { (make) -> Void in
            make.height.equalTo(self.tableView.snp.height)
            make.width.equalTo(self.tableView.snp.width)
            make.leading.equalTo(self.view.snp.leading)
            make.trailing.equalTo(self.view.snp.trailing)
            mapTopConstraint = make.top.equalTo(-self.tableView.frame.height).constraint
        }
    }

    private func configureBarButtonItem() {
        let attributes = [NSFontAttributeName: UIFont.init(name: "FontAwesome", size: 20.0)!]
        toggleButton.setTitleTextAttributes(attributes, for: .normal)
        toggleButton.title = "\u{f279}"
    }

    private func loadAnnotations(annotations: [MKAnnotation]) {
        for annotation in annotations {
            mapView?.addAnnotation(annotation)
        }
        mapView?.showAnnotations(annotations, animated: false)
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let index = sender as? Int,
            let destination = segue.destination as? ServiceViewController,
            segue.identifier == "showService" else { return }
        switch state {
        case .loaded(let departments):
            destination.departmentId = departments[index].uid
        default:
            break
        }
        if let selectedIndex = tableView.indexPathForSelectedRow {
            tableView.deselectRow(at: selectedIndex, animated: true)
        }
    }

    @IBAction func toggleMap(_ sender: UIBarButtonItem) {
        mapTopConstraint?.deactivate()
        mapView?.snp.makeConstraints { (make) -> Void in
            switch viewState {
            case .list:
                mapTopConstraint = make.top.equalTo(self.tableView.snp.top).constraint
                toggleButton.title = "\u{f0ca}"
                viewState = .map
            case .map:
                mapTopConstraint = make.top.equalTo(-self.tableView.frame.height).constraint
                toggleButton.title = "\u{f279}"
                viewState = .list
            }
        }
        view.setNeedsUpdateConstraints()
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
        }
    }
}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch state {
        case .loaded(let departments):
            let cell = tableView.dequeueReusableCell(withIdentifier: "departmentCell", for: indexPath) as UITableViewCell
            cell.textLabel?.text = departments[indexPath.row].name
            return cell
        default:
            break
        }
        return UITableViewCell()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch state {
        case .loaded(let departments):
            return departments.count
        default:
            return 1
        }
    }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "showService", sender: indexPath.row)
    }
}

extension ViewController: MKMapViewDelegate {
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        let newAnnotation = MKPinAnnotationView(annotation: annotation, reuseIdentifier: "pinAnnotation")
        newAnnotation.canShowCallout = true
        newAnnotation.rightCalloutAccessoryView = UIButton(type: .detailDisclosure)
        return newAnnotation
    }
}
